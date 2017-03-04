package com.dreamproduction.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Ruslan on 03.03.2017.
 */
public class SpeechKitProvider {

    private static final String URL = "/asr_xml?";
    private static final String API_KEY = "8b2415ac-5b77-4b6e-b0e1-3496a8ffbf3c";
    private static final String HOST = "asr.yandex.net";
    private static final String UUID = "423a423a423a423a423a423a423a423a";
    private static HttpURLConnection connection;

    public static String doPost(File voiceMessage) throws IOException {

        sendRequest(voiceMessage);
        return getResponse();

    }

    private static void sendRequest(File voiceMessage) throws IOException {
        Path music = Paths.get(voiceMessage.getAbsolutePath());
        byte[] byteArray = Files.readAllBytes(music);
        String urlParameters = "UUID=" + UUID + "&key=" + API_KEY + "&topic=queries&lang=ru-RU";

        URL apiUrl = new URL("https://" + HOST + URL + urlParameters);

        connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Host", HOST);
        connection.setRequestProperty("Content-Type", "audio/ogg;codecs=opus");
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.write(byteArray);
        wr.flush();
        wr.close();
    }

    private static String getResponse() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String inputLine;
        StringBuilder builder = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            builder.append(inputLine);
        in.close();
        return builder.toString();
    }
}
