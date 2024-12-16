package com.example.hotal.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.example.hotal.service.ReportService;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReportServiceImpl implements ReportService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Map<String, Double> getDailySales(String filePath) {
        Map<String, Double> dailySales = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // Skip header
            while ((line = reader.readNext()) != null) {
                if (line.length < 7) {
                    // Skip lines that do not have at least 2 columns
                    continue;
                }
                String date = line[3]; // Assuming CheckIn is the first column
                double amount;
                try {
                    amount = Double.parseDouble(line[6]); // Assuming Amount is the second column
                } catch (NumberFormatException e) {
                    // Skip lines where the amount is not a valid number
                    continue;
                }

                dailySales.put(date, dailySales.getOrDefault(date, 0.0) + amount);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        // Sort the map by date
        Map<String, Double> sortedDailySales = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                try {
                    return dateFormat.parse(o1).compareTo(dateFormat.parse(o2));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        sortedDailySales.putAll(dailySales);

        return sortedDailySales;
    }

    public Map<String, Double> getMonthlySales(String filePath) {
        Map<String, Double> monthlySales = new HashMap<>();
        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // Skip header
            while ((line = reader.readNext()) != null) {
                if (line.length < 7) {
                    // Skip lines that do not have at least 2 columns
                    continue;
                }
                String date = line[3]; // Assuming CheckIn is the first column
                double amount;
                try {
                    amount = Double.parseDouble(line[6]); // Assuming Amount is the second column
                } catch (NumberFormatException e) {
                    // Skip lines where the amount is not a valid number
                    continue;
                }

                String month = monthFormat.format(dateFormat.parse(date));
                monthlySales.put(month, monthlySales.getOrDefault(month, 0.0) + amount);
            }
        } catch (IOException | CsvValidationException | ParseException e) {
            e.printStackTrace();
        }

        // Sort the map by month
        Map<String, Double> sortedMonthlySales = new TreeMap<>(monthlySales);

        return sortedMonthlySales;
    }

    public Map<String, Double> getLastMonthDailySales(String filePath) {
        Map<String, Double> dailySales = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
        String lastMonth = monthFormat.format(calendar.getTime());

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // Skip header
            while ((line = reader.readNext()) != null) {
                if (line.length < 7) {
                    // Skip lines that do not have at least 2 columns
                    continue;
                }
                String date = line[3]; // Assuming CheckIn is the first column
                double amount;
                try {
                    amount = Double.parseDouble(line[6]); // Assuming Amount is the second column
                } catch (NumberFormatException e) {
                    // Skip lines where the amount is not a valid number
                    continue;
                }

                if (date.startsWith(lastMonth)) {
                    dailySales.put(date, dailySales.getOrDefault(date, 0.0) + amount);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        // Sort the map by date
        Map<String, Double> sortedDailySales = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                try {
                    return dateFormat.parse(o1).compareTo(dateFormat.parse(o2));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        sortedDailySales.putAll(dailySales);

        return sortedDailySales;
    }

    public Map<String, Integer> getRoomSelection(String filePath) {
        Map<String, Integer> roomSelection = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // Skip header
            while ((line = reader.readNext()) != null) {
                if (line.length < 3) {
                    // Skip lines that do not have at least 3 columns
                    continue;
                }
                String roomType = line[0]; // Assuming RoomType is the first column
                String roomSize = line[1]; // Assuming RoomSize is the second column
                int selectionCount;
                try {
                    selectionCount = Integer.parseInt(line[2]); // Assuming SelectionCount is the third column
                } catch (NumberFormatException e) {
                    // Skip lines where the selection count is not a valid number
                    continue;
                }

                String key = roomType + " - " + roomSize;
                roomSelection.put(key, roomSelection.getOrDefault(key, 0) + selectionCount);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return roomSelection;
    }
}