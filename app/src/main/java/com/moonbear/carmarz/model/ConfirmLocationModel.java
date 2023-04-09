package com.moonbear.carmarz.model;

import java.io.Serializable;

public class ConfirmLocationModel implements Serializable {
    public String title;
    public String additionalInfo;
    public double lat, lng;
    public String placeId;
    public String id;
    public String isLiked;
}
