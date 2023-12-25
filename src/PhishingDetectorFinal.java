import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PhishingDetectorFinal {

    public static void main(String[] args) {
        // Mapa para almacenar las palabras y su puntaje
        Map<String, Integer> phishingTerms = new HashMap<>();

        // Agregar palabras y puntajes al mapa
        phishingTerms.put("etiqueta", 2);
        phishingTerms.put("factura", 2);
        phishingTerms.put("publicación", 2);
        phishingTerms.put("documento", 2);
        phishingTerms.put("postal", 2);
        phishingTerms.put("cálculos", 2);
        phishingTerms.put("copia", 2);
        phishingTerms.put("fedex", 2);
        phishingTerms.put("declaración", 2);
        phishingTerms.put("financiero", 2);
        phishingTerms.put("dhl", 3);
        phishingTerms.put("usps", 2);
        phishingTerms.put("notificación", 2);
        phishingTerms.put("norte", 2);
        phishingTerms.put("irs", 3);
        phishingTerms.put("subidas", 2);
        phishingTerms.put("no", 1);
        phishingTerms.put("entrega", 2);
        phishingTerms.put("billete", 2);
        phishingTerms.put("Microsoft", 3);
        phishingTerms.put("DHL", 3);
        phishingTerms.put("PayPal", 3);
        phishingTerms.put("Netflix", 3);
        phishingTerms.put("Facebook", 3);
        phishingTerms.put("Apple", 3);
        phishingTerms.put("WhatsApp", 3);
        phishingTerms.put("Amazon", 3);
        phishingTerms.put("Instagram", 3);
        phishingTerms.put("Bancos", 3);
        phishingTerms.put("Decir que usted tiene que confirmar algún dato personal o financiero, pero usted no tiene que hacerlo.", 2);
        phishingTerms.put("Ofrecerle un cupón para conseguir algo gratis, pero eso no es cierto.", 2);


        // Ruta del archivo de texto a analizar
        String filePath ="C:/actividad6phishing/Actividad 6/Archivo.txt";

        // Realizar el análisis del archivo
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            int totalPointsForMessage = 0; // Variable para rastrear el total de puntos para todo el mensaje

            while ((line = reader.readLine()) != null) {
                // Tokenizar la línea en palabras
                String[] words = line.split("\\s+");

                // Verificar cada palabra contra el mapa de términos de phishing
                for (String word : words) {
                    // Convertir la palabra a minúsculas para hacer la comparación sin distinción entre mayúsculas y minúsculas
                    String lowercaseWord = word.toLowerCase();

                    // Verificar si la palabra está en el mapa de términos de phishing
                    if (phishingTerms.containsKey(lowercaseWord)) {
                        // Sumar el puntaje correspondiente al término encontrado
                        int currentScore = phishingTerms.get(lowercaseWord);
                        phishingTerms.put(lowercaseWord, currentScore + 1);

                        // Sumar al total de puntos para todo el mensaje
                        totalPointsForMessage += currentScore;
                    }
                }
            }

            // Imprimir los resultados
            for (Map.Entry<String, Integer> entry : phishingTerms.entrySet()) {
                System.out.println("Palabra: " + entry.getKey() + ", Ocurrencias: " + entry.getValue() + ", Puntaje: " + (entry.getValue() * phishingTerms.get(entry.getKey())));
            }

            // Calcular y imprimir el total de puntos para todo el mensaje
            System.out.println("Total de puntos para todo el mensaje: " + totalPointsForMessage);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}