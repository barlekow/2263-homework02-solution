package edu.isu.cs.cs2263.hw02.views;

import javafx.scene.Node;

public interface IAppView {

    Node getView();

    void initView();

    void updateData();
}
