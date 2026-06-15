package com.uni.app.Application.Model;

import java.util.List;

import lombok.Data;

@Data
public class ITunesResponse {
    private int resultCount;
    private List<ITunesTrack> results;
}