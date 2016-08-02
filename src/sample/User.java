package sample;

import java.io.Serializable;

/**
 * Created by MSI on 21.02.2016.
 */
public class User implements Serializable
{
    private String name;
    private String pass;
    private int lvl;

    private long date;

    public User(String name, String pass, int lvl) {
        this.name = name;
        this.pass = pass;
        this.lvl = lvl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getLvl() {
        return lvl;
    }

    public void levelPassed()
    {
        lvl++;
        System.out.println(lvl+"User max level");
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    long diffSeconds = date / 1000 % 60;
    long diffMinutes = date / (60 * 1000) % 60;
    long diffHours = date / (60 * 60 * 1000) % 24;
    long diffDays = date / (24 * 60 * 60 * 1000);

}
