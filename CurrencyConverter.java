package org.example;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConverter {
    private static final String API_URL = "https://openexchangerates.org/api/latest.json";
    private static final String API_KEY = "6dfa0359199148f6a88c600a7d28d33d";

    public static double getExchangeRate(String fromCurrency, String toCurrency) {
        double exchangeRate = 1.0;
        try {
            URL url = new URL(API_URL + "?app_id=" + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder inline = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    inline.append(scanner.nextLine());
                }
                scanner.close();

                JSONObject data = new JSONObject(inline.toString());
                JSONObject rates = data.getJSONObject("rates");
                double fromRate = rates.getDouble(fromCurrency);
                double toRate = rates.getDouble(toCurrency);

                exchangeRate = toRate / fromRate;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exchangeRate;
    }
}

