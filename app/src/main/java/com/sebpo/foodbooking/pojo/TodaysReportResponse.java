package com.sebpo.foodbooking.pojo;

public class TodaysReportResponse {
    private String message;

    private String status;

    private Served served;

    private String code;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public Served getServed ()
    {
        return served;
    }

    public void setServed (Served served)
    {
        this.served = served;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", status = "+status+", served = "+served+", code = "+code+"]";
    }
}
