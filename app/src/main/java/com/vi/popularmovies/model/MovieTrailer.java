package com.vi.popularmovies.model;

public class MovieTrailer {
    private static final String YOUTUBE_BASE = "https://www.youtube.com/watch?v=";

    private String trailerId;
    private String name;
    //"iso_639_1"
    private String languageCode;
    //"iso_3166_1"
    private String countryCode;
    private String key;
    private String site;
    private String size;
    private String type;
    private String url;

    public MovieTrailer (String trailerId, String name, String languageCode, String countryCode, String key, String site, String size, String type) {
        this.trailerId = trailerId;
        this.name = name;
        this.languageCode = languageCode;
        this.countryCode = countryCode;
        this.key = key;
        this.site = site;
        this.size = size;
        this.type = type;
        this.url = YOUTUBE_BASE + key;
    }

    public String getTrailerId() { return trailerId; }

    public String getName() { return name; }

    public String getLanguageCode() { return languageCode; }

    public String getCountryCode() { return countryCode; }

    public String getKey() { return key; }

    public String getSite() { return site; }

    public String getSize() { return size; }

    public String getType() { return type; }

    public String getUrl() { return url; }
}
