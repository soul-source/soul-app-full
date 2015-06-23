/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.news.controller;

import system.common.MyJFrameControllerChild;
import system.news.model.TableModelNews;
import system.news.view.JFrameNews;

/**
 *
 *
 */
public class ControllerJFrameNews extends MyJFrameControllerChild {

    public ControllerJFrameNews(int idChat, ControllerJFrameNewsList owner, boolean createNew) {
        super(owner, new TableModelNews(idChat,owner.getPermissions(createNew)));
        this.initialize(new JFrameNews(this, createNew));
    }
}
