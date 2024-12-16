package com.example.hotal.controller;

import com.example.hotal.entity.Customer;
import com.example.hotal.entity.EsportsEquipment;
import com.example.hotal.entity.HotelRoom;
import com.example.hotal.entity.Order;
import com.example.hotal.mapper.EsportsEquipmentMapper;
import com.example.hotal.mapper.HotelRoomMapper;
import com.example.hotal.service.HotelRoomService;
import com.example.hotal.service.OrderService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Data
@Controller
@RequestMapping("/rooms")
public class HotelRoomController {
    @Autowired
    private HotelRoomService hotelRoomService;
    @Autowired
    private HotelRoomMapper hotelRoomMapper;
    @Autowired
    private OrderService orderService;

    @PostMapping("/search")
    public String findRoom(@RequestParam String roomType,
                           @RequestParam Integer roomCapacity,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkInTime,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkOutTime,
                           Model model) {
        List<HotelRoom> Rooms = hotelRoomService.findFreeRoom(roomType, roomCapacity, checkInTime, checkOutTime);
        for (HotelRoom room : Rooms) {
            if (room.getImg1() != null) {
                room.setImg1Base64(Base64.getEncoder().encodeToString(room.getImg1()));
            }
            if (room.getImg2() != null) {
                room.setImg2Base64(Base64.getEncoder().encodeToString(room.getImg2()));
            }
            if (room.getImg3() != null) {
                room.setImg3Base64(Base64.getEncoder().encodeToString(room.getImg3()));
            }
        }
        model.addAttribute("checkInTime", checkInTime);
        model.addAttribute("checkOutTime", checkOutTime);
        model.addAttribute("Rooms", Rooms);
        return "rooms";
    }
    @GetMapping("/select")
    public String getRoomById(@RequestParam Integer roomId, Model model) {
        HotelRoom hotelRoom =hotelRoomService.getHotelRoomById(roomId);
        model.addAttribute("hotelRoom", hotelRoom);
        return "hotelmanage";
    }

    @GetMapping
    public String getAllRoom(Model model) {
        List<HotelRoom> allRoom =hotelRoomService.getAllHotelRooms();
        model.addAttribute("allRoom", allRoom);
        return "hotelmanage";
    }

    @RequestMapping("/update")
    public String updateOrder(
            @RequestParam Integer updateId,
            @RequestParam String roomNumber,
            @RequestParam String roomType,
            @RequestParam Integer roomCapacity,
            @RequestParam String roomStatus,
            @RequestParam String bedSize,
            @RequestParam String otherInfo,
            @RequestParam Double price) {

        HotelRoom hotelRoom = new HotelRoom();
        hotelRoom.setRoom_id(updateId);
        hotelRoom.setRoom_number(roomNumber);
        hotelRoom.setRoom_type(roomType);
        hotelRoom.setRoom_capacity(roomCapacity);
        hotelRoom.setRoom_status(roomStatus);
        hotelRoom.setBed_size(bedSize);
        hotelRoom.setOther_info(otherInfo);
        hotelRoom.setPrice(price);
        hotelRoomService.updateHotelRoom(hotelRoom);
        return "redirect:/rooms";
    }
    @GetMapping("/")
    public String init(Model model) {
        LocalDateTime checkInTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0));
        model.addAttribute("checkInTime", checkInTime);

// 设置CheckOutTime为当前时间的后一天的12:00
        LocalDateTime checkOutTime = LocalDateTime.now().plusDays(1).withHour(12).withMinute(0).withSecond(0).withNano(0);
        model.addAttribute("checkOutTime", checkOutTime);
        return "rooms";
    }


        public byte[] convertFileToByteArray(File file) {
            byte[] fileData = null;
            try (FileInputStream fis = new FileInputStream(file)) {
                fileData = new byte[(int) file.length()];
                int read = fis.read(fileData);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return fileData;
        }
//测试用接口，由于数据库中存在图片数据，必须通过接口存储，此类用于存入房间数据
        @GetMapping("/testInsert")
        public String insertRoomTest() {
            List<HotelRoom> Rooms = new ArrayList<>();
            for (int i = 0; i <= 4; i++) {
                File img1 = new File("D:\\2exec\\hotal\\src\\main\\resources\\static\\img1.png");
                File img2 = new File("D:\\2exec\\hotal\\src\\main\\resources\\static\\img2.jpg");
                File img3 = new File("D:\\2exec\\hotal\\src\\main\\resources\\static\\img3.jpg");
                byte[] img1Data = convertFileToByteArray(img1);
                byte[] img2Data = convertFileToByteArray(img2);
                byte[] img3Data = convertFileToByteArray(img3);
                HotelRoom hotelRoom = new HotelRoom(4, "104", "卓威豪华套房", 1, "available", "King Size", "Spacious room with a view", 150.0, img1Data, img2Data, img3Data,"","","");
                Rooms.add(hotelRoom);
            }
            hotelRoomMapper.insertHotelRooms(Rooms);
            return "rooms";
        }
        @GetMapping("/detail")
        public String roomDetail(@RequestParam("room_id") Integer room_id,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkInTime ,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkOutTime,
                                 Model model)
        {
                HotelRoom room=hotelRoomMapper.getHotelRoomById(room_id);
                List<EsportsEquipment> displays = hotelRoomService.groupRoomEquipmentByType("显示器",room_id);
                List<EsportsEquipment> gpus = hotelRoomService.groupRoomEquipmentByType("显卡",room_id);
                List<EsportsEquipment> cpus = hotelRoomService.groupRoomEquipmentByType("处理器",room_id);
                List<EsportsEquipment> keyboards= hotelRoomService.groupRoomEquipmentByType("键盘",room_id);
                List<EsportsEquipment> mouses = hotelRoomService.groupRoomEquipmentByType("鼠标",room_id);
                if (room.getImg1() != null) {
                    room.setImg1Base64(Base64.getEncoder().encodeToString(room.getImg1()));
                }
                if (room.getImg2() != null) {
                    room.setImg2Base64(Base64.getEncoder().encodeToString(room.getImg2()));
                }
                if (room.getImg3() != null) {
                    room.setImg3Base64(Base64.getEncoder().encodeToString(room.getImg3()));
                }
                model.addAttribute("checkInTime", checkInTime);
                model.addAttribute("checkOutTime", checkOutTime);
                model.addAttribute("room",room);
                model.addAttribute("displays",displays);
                model.addAttribute("gpus",gpus);
                model.addAttribute("cpus",cpus);
                model.addAttribute("keyboards",keyboards);
                model.addAttribute("mouses",mouses);

                return "detail";
        }
        @GetMapping("/bookRoom")
        public String bookRoom(@RequestParam("roomId") Integer roomId,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkInTime ,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkOutTime,
                               RedirectAttributes redirectAttributes, HttpSession session,Model model)
        {
            Customer customer=(Customer)session.getAttribute("user");
            orderService.bookRoom(customer.getCustomer_id(),roomId,checkInTime,checkOutTime);
            redirectAttributes.addFlashAttribute("successMessage", "预定成功！您的房间号为：" + hotelRoomService.getRoomNumber(roomId));
            return "redirect:/rooms/";
        }
    }