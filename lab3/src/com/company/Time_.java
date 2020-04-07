package com.company;

public class Time_ implements Comparable<Time_> {
    public int hour;
    public int min;
    public int sec;
    public int mlsec;
    //public setTime(int h, int m, int s, int ml)
    //{
    //    hour = h;
    //    min = m;
    //    sec = s;
    //    mlsec = ml;
    //}

    public Time_()
    {
        hour = StdRandom.uniform(0,24);
        min = StdRandom.uniform(0,60);
        sec = StdRandom.uniform(0,60);
        mlsec = StdRandom.uniform(0,1000);
    }
    public int getHour() { return hour; }
    public int getMin() { return min ; }
    public int getSec() { return sec; }
    public int getMlsec() { return mlsec; }
    public int compareTo(Time_ that)
    {
        if(this.hour > that.hour) return 1;
        if(this.hour < that.hour) return -1;
        if(this.min > that.min) return 1;
        if(this.min < that.min) return -1;
        if(this.sec > that.sec) return 1;
        if(this.sec < that.sec) return -1;
        if(this.mlsec > that.mlsec) return 1;
        if(this.mlsec < that.mlsec) return -1;
        return 0;
    }
    public String toString()
    { return hour + ":" + min + ":" + sec + ":" + mlsec; }

}

