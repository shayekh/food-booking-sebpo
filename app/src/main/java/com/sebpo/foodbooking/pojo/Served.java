package com.sebpo.foodbooking.pojo;

public class Served {

    private String dinner;

    private String vendorName;

    private String lunch;

    private String vendorId;

    public String getDinner ()
    {
        return dinner;
    }

    public void setDinner (String dinner)
    {
        this.dinner = dinner;
    }

    public String getVendorName ()
    {
        return vendorName;
    }

    public void setVendorName (String vendorName)
    {
        this.vendorName = vendorName;
    }

    public String getLunch ()
    {
        return lunch;
    }

    public void setLunch (String lunch)
    {
        this.lunch = lunch;
    }

    public String getVendorId ()
    {
        return vendorId;
    }

    public void setVendorId (String vendorId)
    {
        this.vendorId = vendorId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dinner = "+dinner+", vendorName = "+vendorName+", lunch = "+lunch+", vendorId = "+vendorId+"]";
    }
}
