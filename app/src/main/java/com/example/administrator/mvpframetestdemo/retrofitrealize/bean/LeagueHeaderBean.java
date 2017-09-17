package com.example.administrator.mvpframetestdemo.retrofitrealize.bean;

import java.util.List;

/**
 * Created by：XQyi on 2017/9/9 14:36
 * Use:
 */
public class LeagueHeaderBean {

    // http://m.13322.com/mlottery/core/androidLeagueData.findAndroidFootballLeagueHeader.do?lang=zh&timeZone=8&type=0&leagueId=60
    /**
     * code : 200
     * season : ["2017","2016","2015","2014","2013","2012","2011","2010","2009","2008","2007","2006","2005","2004"]
     * matchType : 0
     * leagueId : 60
     * randomBg : http://pic.13322.com/bg/16.png
     * leagueLogo : http://pic.13322.com/icons/league/60.png
     * leagueName : 中超
     */

    private int code;
    private int matchType;
    private int leagueId;
    private String randomBg;
    private String leagueLogo;
    private String leagueName;
    private List<String> season;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getMatchType() {
        return matchType;
    }

    public void setMatchType(int matchType) {
        this.matchType = matchType;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public String getRandomBg() {
        return randomBg;
    }

    public void setRandomBg(String randomBg) {
        this.randomBg = randomBg;
    }

    public String getLeagueLogo() {
        return leagueLogo;
    }

    public void setLeagueLogo(String leagueLogo) {
        this.leagueLogo = leagueLogo;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public List<String> getSeason() {
        return season;
    }

    public void setSeason(List<String> season) {
        this.season = season;
    }
}
