package org.academiadecodigo.bootcamp.server;

public enum SubChat {
    GENERAL(" [General]"),
    CATS(" [Cats]"),
    GOOSE(" [Geese]");

    String channelName;

    SubChat(String channelName){
        this.channelName = channelName;
    }
}
