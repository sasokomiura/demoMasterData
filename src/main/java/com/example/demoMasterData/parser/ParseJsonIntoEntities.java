package com.example.demoMasterData.parser;

public class ParseJsonIntoEntities {

    private String source;

    public String getSource() {
        return source;
    }

    public String getValue(String key) {
        if (!this.getSource().contains(key))
            return "";
        else {
            Integer l = this.getSource().indexOf(key);
            l += key.length();
            l = this.getSource().indexOf("\"", l + 1);
            l += 1;
            Integer r = this.getSource().indexOf("\"", l + 1);
            String result = this.getSource().substring(l, r);
            return result;
        }
    }

    public ParseJsonIntoEntities(String source) {
        this.source = source;
    }
}
