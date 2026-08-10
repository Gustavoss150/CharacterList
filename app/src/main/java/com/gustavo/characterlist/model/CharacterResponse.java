package com.gustavo.characterlist.model;

import java.util.List;

public class CharacterResponse {

    private List<Characters> results;

    public List<Characters> getResults() {
        return results;
    }

    public void setResults(List<Characters> results) {
        this.results = results;
    }
}