package edu.upc.eetac.dsa.dsaqt1415g6.restaurapp.api;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by xavi on 14/12/14.
 */
public class RestaurappAPI {

    private final static String TAG = RestaurappAPI.class.getName();
    private static RestaurappAPI instance = null;
    private URL url;

    private RestaurappRootAPI rootAPI = null;

    private RestaurappAPI(Context context) throws IOException, AppException {
        super();

        AssetManager assetManager = context.getAssets();
        Properties config = new Properties();
        config.load(assetManager.open("config.properties"));
        String urlHome = config.getProperty("restaurapp.home");
        url = new URL(urlHome);

        Log.d("LINKS", url.toString());
        getRootAPI();
    }

    public final static RestaurappAPI getInstance(Context context) throws AppException {
        if (instance == null)
            try {
                instance = new RestaurappAPI(context);
            } catch (IOException e) {
                throw new AppException(
                        "Can't load configuration file");
            }
        return instance;
    }

    private void getRootAPI() throws AppException {
        Log.d(TAG, "getRootAPI()");
        rootAPI = new RestaurappRootAPI();
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
        } catch (IOException e) {
            throw new AppException(
                    "Can't connect to Restaurantes API Web Service");
        }

        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonLinks = jsonObject.getJSONArray("links");
            parseLinks(jsonLinks, rootAPI.getLinks());
        } catch (IOException e) {
            throw new AppException(
                    "Can't get response from Libros API Web Service");
        } catch (JSONException e) {
            throw new AppException("Error parsing Libros Root API");
        }

    }

    public RestauranteCollection getRestaurantes() throws AppException {
        Log.d(TAG, "getLibros()");
        RestauranteCollection restaurantes = new RestauranteCollection();

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) new URL(rootAPI.getLinks()
                    .get("restaurantes").getTarget()).openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
        } catch (IOException e) {
            throw new AppException(
                    "Can't connect to Libros API Web Service");
        }

        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonLinks = jsonObject.getJSONArray("links");
            parseLinks(jsonLinks, restaurantes.getLinks());

            restaurantes.setNewestTimestamp(jsonObject.getLong("newestTimestamp"));
            restaurantes.setOldestTimestamp(jsonObject.getLong("oldestTimestamp"));
            JSONArray jsonRestaurantes = jsonObject.getJSONArray("restaurantes");
            for (int i = 0; i < jsonRestaurantes.length(); i++) {

                JSONObject jsonRestaurante = jsonRestaurantes.getJSONObject(i);
                Restaurante restaurante;

                if (restaurantes.getRestaurante(jsonRestaurante.getInt("idrestaurante"))==null) {


                    restaurante = new Restaurante();

                    restaurante.setIdRestaurante(jsonRestaurante.getInt("idrestaurante"));
                    restaurante.setCategoria(jsonRestaurante.getString("categoria"));
                    restaurante.setCreador(jsonRestaurante.getString("creador"));
                    restaurante.setDireccion(jsonRestaurante.getString("direccion"));
                    restaurante.setCreationTimeStamp(jsonRestaurante.getLong("creationTime"));
                    restaurante.setEmail(jsonRestaurante.getString("email"));
                    restaurante.setHorario(jsonRestaurante.getString("horario"));
                    restaurante.setNombre(jsonRestaurante.getString("nombre"));
                    restaurante.setProvincia(jsonRestaurante.getString("provincia"));
                    restaurante.setTelefono(jsonRestaurante.getString("telefono"));
                    jsonLinks = jsonRestaurante.getJSONArray("links");
                    parseLinks(jsonLinks, restaurante.getLinks());
                }
                else{

                    restaurante = restaurantes.getRestaurante(jsonRestaurante.getInt("idrestaurante"));

                }

                JSONArray jsonOpiniones = jsonRestaurante.getJSONArray("opiniones");
                for (int j = 0; j < jsonOpiniones.length(); j++) {

                    Opinion opinion = new Opinion();
                    JSONObject jsonOpinion = jsonOpiniones.getJSONObject(j);
                    opinion.setFecha_estancia(jsonOpinion.getString("fecha_estancia"));
                    opinion.setCreation_timestamp(jsonOpinion.getLong("creation_timestamp"));
                    opinion.setIdopinion(jsonOpinion.getInt("idopinion"));
                    opinion.setIdrestaurante(jsonOpinion.getInt("idrestaurante"));
                    opinion.setNoutilidad(jsonOpinion.getInt("noutilidad"));
                    opinion.setPuntuacion(jsonOpinion.getInt("puntuacion"));
                    opinion.setTexto(jsonOpinion.getString("texto"));
                    opinion.setTitulo(jsonOpinion.getString("titulo"));
                    opinion.setUsername(jsonOpinion.getString("username"));
                    opinion.setUtilidad(jsonOpinion.getInt("utilidad"));

                    restaurante.addOpinion(opinion);

                }

                if (restaurantes.getRestaurante(jsonRestaurante.getInt("idrestaurante"))==null){
                    restaurantes.addRestaurantes(restaurante);
                }

            }
        } catch (IOException e) {
            throw new AppException(
                    "Can't get response from Libros API Web Service");
        } catch (JSONException e) {
            throw new AppException("Error parsing Libros Root API");
        }

        return restaurantes;
    }

    private Map<String, Restaurante> opinionesCache = new HashMap<String, Restaurante>();

    public Restaurante getOpiniones(String urlRestaurante) throws AppException {
        Restaurante opiniones = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlRestaurante);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);

            opiniones = opinionesCache.get(urlRestaurante);
            String eTag = (opiniones == null) ? null : opiniones.getETag();
            if (eTag != null)
                urlConnection.setRequestProperty("If-None-Match", eTag);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_NOT_MODIFIED) {
                Log.d(TAG, "CACHE");
                return restauranteCache.get(urlRestaurante);
            }
            Log.d(TAG, "NOT IN CACHE");
            opiniones = new Restaurante();
            eTag = urlConnection.getHeaderField("ETag");
            opiniones.setETag(eTag);
            restauranteCache.put(urlRestaurante, opiniones);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            JSONObject jsonRestaurante = new JSONObject(sb.toString());
            JSONArray jsonOpiniones = jsonRestaurante.getJSONArray("opiniones");

            for(int j=0;j<jsonOpiniones.length();j++){
                Opinion opinion = new Opinion();
                JSONObject jsonOpinion = jsonOpiniones.getJSONObject(j);
                opinion.setFecha_estancia(jsonOpinion.getString("fecha_estancia"));
                opinion.setCreation_timestamp(jsonOpinion.getLong("creation_timestamp"));
                opinion.setIdopinion(jsonOpinion.getInt("idopinion"));
                opinion.setIdrestaurante(jsonOpinion.getInt("idrestaurante"));
                opinion.setNoutilidad(jsonOpinion.getInt("noutilidad"));
                opinion.setPuntuacion(jsonOpinion.getInt("puntuacion"));
                opinion.setTexto(jsonOpinion.getString("texto"));
                opinion.setTitulo(jsonOpinion.getString("titulo"));
                opinion.setUsername(jsonOpinion.getString("username"));
                opinion.setUtilidad(jsonOpinion.getInt("utilidad"));

                opiniones.addOpinion(opinion);

            }

        } catch (MalformedURLException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Bad sting url");
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Exception when getting the sting");
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Exception parsing response");
        }

        return opiniones;
    }




    private Map<String, Restaurante> restauranteCache = new HashMap<String, Restaurante>();

    public Restaurante getRestaurante(String urlRestaurante) throws AppException {
        Restaurante restaurante = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlRestaurante);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);

            restaurante = restauranteCache.get(urlRestaurante);
            String eTag = (restaurante == null) ? null : restaurante.getETag();
            if (eTag != null)
                urlConnection.setRequestProperty("If-None-Match", eTag);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_NOT_MODIFIED) {
                Log.d(TAG, "CACHE");
                return restauranteCache.get(urlRestaurante);
            }
            Log.d(TAG, "NOT IN CACHE");
            restaurante = new Restaurante();
            eTag = urlConnection.getHeaderField("ETag");
            restaurante.setETag(eTag);
            restauranteCache.put(urlRestaurante, restaurante);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            JSONObject jsonRestaurante = new JSONObject(sb.toString());
            restaurante.setIdRestaurante(jsonRestaurante.getInt("idrestaurante"));
            restaurante.setCategoria(jsonRestaurante.getString("categoria"));
            restaurante.setCreador(jsonRestaurante.getString("creador"));
            restaurante.setDireccion(jsonRestaurante.getString("direccion"));
            restaurante.setCreationTimeStamp(jsonRestaurante.getLong("creationTime"));
            restaurante.setEmail(jsonRestaurante.getString("email"));
            restaurante.setHorario(jsonRestaurante.getString("horario"));
            restaurante.setNombre(jsonRestaurante.getString("nombre"));
            restaurante.setProvincia(jsonRestaurante.getString("provincia"));
            restaurante.setTelefono(jsonRestaurante.getString("telefono"));
            JSONArray jsonLinks = jsonRestaurante.getJSONArray("links");
            parseLinks(jsonLinks, restaurante.getLinks());
            JSONArray jsonOpiniones = jsonRestaurante.getJSONArray("opiniones");

            for(int j=0;j<jsonOpiniones.length();j++){
                Opinion opinion = new Opinion();
                JSONObject jsonOpinion = jsonOpiniones.getJSONObject(j);
                opinion.setFecha_estancia(jsonOpinion.getString("fecha_estancia"));
                opinion.setCreation_timestamp(jsonOpinion.getLong("creation_timestamp"));
                opinion.setIdopinion(jsonOpinion.getInt("idopinion"));
                opinion.setIdrestaurante(jsonOpinion.getInt("idrestaurante"));
                opinion.setNoutilidad(jsonOpinion.getInt("noutilidad"));
                opinion.setPuntuacion(jsonOpinion.getInt("puntuacion"));
                opinion.setTexto(jsonOpinion.getString("texto"));
                opinion.setTitulo(jsonOpinion.getString("titulo"));
                opinion.setUsername(jsonOpinion.getString("username"));
                opinion.setUtilidad(jsonOpinion.getInt("utilidad"));

                restaurante.addOpinion(opinion);

            }

        } catch (MalformedURLException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Bad sting url");
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Exception when getting the sting");
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new AppException("Exception parsing response");
        }

        return restaurante;
    }




    private void parseLinks(JSONArray jsonLinks, Map<String, Link> map)
            throws AppException, JSONException {
        for (int i = 0; i < jsonLinks.length(); i++) {
            Link link = null;
            try {
                link = SimpleLinkHeaderParser
                        .parseLink(jsonLinks.getString(i));
            } catch (Exception e) {
                throw new AppException(e.getMessage());
            }
            String rel = link.getParameters().get("rel");
            String rels[] = rel.split("\\s");
            for (String s : rels)
                map.put(s, link);
        }
    }
}
