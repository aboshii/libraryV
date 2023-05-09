package com.library.springlibrary.controller;

class UrlHandler {
     static String getParameterFromReferer(String url, String parameterName){
        String[] split = url.split(parameterName + "=");
        return split[split.length-1];
    }
}
