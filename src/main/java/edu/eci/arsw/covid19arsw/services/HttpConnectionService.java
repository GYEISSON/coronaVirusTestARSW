package edu.eci.arsw.covid19arsw.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class HttpConnectionService {
    private String headerHostValue;
    private String headerKeyValue;
    private String url;
    private Gson gson;

    public HttpConnectionService(){
    	url = "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=";
        headerHostValue = "covid-19-coronavirus-statistics.p.rapidapi.com";
        headerKeyValue = "9077e18e69mshd0c95d063e40146p183591jsn9f9008035e02";
        setGson(new GsonBuilder().create());
    }
    
    public JSONArray HTTPConnection(String name){

        String encodedUrlName = null;
        try {
            encodedUrlName = URLEncoder.encode(name,java.nio.charset.StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //System.out.println(encodedUrlName);
        StringBuilder apiUrl = new StringBuilder();
        apiUrl.append(url +encodedUrlName);

        HttpResponse<JsonNode> apiResponse = null;
        try{
            apiResponse = Unirest.get(apiUrl.toString())
                    .header("x-rapidapi-host", headerHostValue)
                    .header("x-rapidapi-key", headerKeyValue)
                    .asJson();
        }catch (UnirestException e){
            e.printStackTrace();
        }
        //revisar
        JSONArray stats = apiResponse.getBody().getObject().getJSONObject("data").getJSONArray("covid19Stats");
        return stats;
    }

    public String getHeaderHostValue() {
        return headerHostValue;
    }

    public void setHeaderHostValue(String headerHostValue) {
        this.headerHostValue = headerHostValue;
    }

    public String getHeaderKeyValue() {
        return headerKeyValue;
    }

    public void setHeaderKeyValue(String headerKeyValue) {
        this.headerKeyValue = headerKeyValue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public  Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
}

