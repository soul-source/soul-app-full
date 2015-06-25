package system.servlet;

import hwcore.modules.java.src.library.database.RecordSet;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import system.news.model.TableModelNewsList;

public class NewsHandler {

    public static ArrayList<RecordSet> handleNewsGet(HttpServletRequest req, HttpServletResponse resp, String type) throws IOException {
        TableModelNewsList newsList = new TableModelNewsList();
        newsList.refreshList("");
        return new ArrayList<>(newsList.getTableData().getRecords());
    }
}
