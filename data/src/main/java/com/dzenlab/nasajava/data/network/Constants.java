package com.dzenlab.nasajava.data.network;

public class Constants {

    public static final String BASE_URL = "https://api.nasa.gov/";

    public static final String PLANETARY_URL = "planetary/apod/";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final long CONNECT_TIMEOUT_SECOND = 10;

    public static final long READ_WRITE_TIMEOUT_SECOND = 30;

    public static final long CACHE_SIZE = 10L * 1024L * 1024L;

    public static final String CACHE_CONTROL_HEADER = "Cache-Control";

    public static final String CACHE_CONTROL_VALUE = "public, max-age=1";

    public static final String CONTENT_TYPE_HEADER = "Content-Type";

    public static final String CONTENT_TYPE_APP_JSON = "application/json";

    public static final String QUERY_API_KEY_KEY = "api_key";

    public static final String QUERY_COUNT_KEY = "count";

    public static final String QUERY_API_KEY_VALUE = "kDdwKaaXS3nqVSZhE63czFodGMynJ8cbIXQ21tmO";

    public static final int QUERY_COUNT_VALUE = 20;

    public static final String DATE_JSON = "date";

    public static final String TITLE_JSON = "title";

    public static final String URL_JSON = "url";
}
