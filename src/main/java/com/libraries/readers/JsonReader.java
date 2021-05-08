package com.libraries.readers;

import com.libraries.API.APIConstants;
import com.libraries.API.RestAPILibrary;
import com.libraries.POJO.Medha.Response.Actions;
import com.libraries.POJO.Medha.Response.DataElementValue;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import java.util.HashMap;
import java.util.Map;

public class JsonReader {

    public String[] getTestElements(String testName, String element){

        String uri = "/api/tests/actions";
        RestAPILibrary rs = new RestAPILibrary(uri, APIConstants.ApiMethods.GET,"");
        Map<String,String> queryParams = new HashMap<String, String>();
        //testName = testName.toLowerCase();
        queryParams.put("name",testName);
        ResponseOptions<Response> response = rs.ExecuteWithQueryParams(queryParams);
        var re = response.getBody().as(Actions.class);
        String act = re.getActions();
        //System.out.println(act);
        String[] pageList = act.split(",");
        int pageCount = pageList.length;
        String[] pages = new String[pageCount];
        String[] actions = new String[pageCount];
        for(int i =0; i<pageCount;i++){
            String val = pageList[i].replace("|",":");
            String[] pa = val.split("::");
            pages[i]=pa[0];
            actions[i]=pa[1];
            //System.out.println(pages[i]+"|"+actions[i]+"\n");
        }
        if(element.equalsIgnoreCase("action")){
            return actions;
        }else{
            return pages;
        }

    }

    public int getIterationCount(String testName){
        String uri = "/api/tests/actions";
        RestAPILibrary rs = new RestAPILibrary(uri, APIConstants.ApiMethods.GET,"");
        Map<String,String> queryParams = new HashMap<String, String>();
        queryParams.put("name",testName);
        ResponseOptions<Response> response = rs.ExecuteWithQueryParams(queryParams);
        var re = response.getBody().as(Actions.class);
        int iterationCount = re.getIterationCount();
        return iterationCount;
    }

    public String getDataElementValue(String testName, String env, int iteration, String elementName){
        String uri = "/api/tests/testData";
        RestAPILibrary rs = new RestAPILibrary(uri, APIConstants.ApiMethods.GET,"");
        Map<String,String> queryParams = new HashMap<String, String>();
        queryParams.put("name",testName);
        queryParams.put("environment",env);
        String iter = Integer.toString(iteration);
        queryParams.put("iteration",iter);
        queryParams.put("dataElement",elementName);

        ResponseOptions<Response> response = rs.ExecuteWithQueryParams(queryParams);
        String re = response.getBody().htmlPath().getString("body");
         return re;


    }



}
