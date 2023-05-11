package com.example.qbhcomics.API;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Docs {
        public static void main(String[] args) throws Exception {
            // Xác định đường dẫn và phương thức HTTP
            String url = "https://example.com/data.txt";
            String method = "GET";

            // Tạo HTTP connection và gửi request đến API web
            URL apiUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();
            conn.setRequestMethod(method);

            // Đọc dữ liệu từ phản hồi HTTP
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Hiển thị dữ liệu txt trong app
            System.out.println(response.toString());
        }
}
