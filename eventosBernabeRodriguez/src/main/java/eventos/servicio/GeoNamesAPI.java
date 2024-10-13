package eventos.servicio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeoNamesAPI {

    // Método para hacer la solicitud a la API de GeoNames
    public String hacerSolicitudGeoNames(String coordenadasGeograficas) {
        StringBuilder response = new StringBuilder();

        try {
            // Extraer latitud y longitud de la cadena recibida
            String[] coordenadas = coordenadasGeograficas.split(",");
            String latitud = coordenadas[0];
            String longitud = coordenadas[1];

            // Crear la URL para consultar el API de GeoNames
            //String urlString = "http://api.geonames.org/findNearbyWikipedia?username=arso&lang=ES&lat=" + latitud + "&lng=" + longitud;
            String urlString = "http://api.geonames.org/findNearbyWikipedia?username=elmanueh&lang=ES&lat=" + latitud + "&lng=" + longitud;
            URL url = new URL(urlString);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            // Leer la respuesta de la API
            InputStream inputStream = conexion.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ALGO");
        }

        return response.toString();
    }
}
