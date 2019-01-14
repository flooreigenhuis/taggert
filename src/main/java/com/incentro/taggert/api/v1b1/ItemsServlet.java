package com.incentro.taggert.api.v1b1;

        import com.google.inject.Singleton;
        import com.incentro.taggert.v1b1.Item;
        import com.incentro.taggert.v1b1.LabelledItem;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;


@Singleton
public class ItemsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Item.ImageLink link = Item.ImageLink.newBuilder()
                .setId("default")
                .setUrl("https://vignette.wikia.nocookie.net/dragonballfanon/images/7/70/Random.png/revision/latest?cb=20161221030547")
                .build();

        Item thisitem = com.incentro.taggert.v1b1.Item.newBuilder()
                .setName("Shark")
                .setImageLink(link)
                .build();

        LabelledItem.Labelling label = LabelledItem.Labelling.newBuilder()
                .addLabels("rainbow shark")
                .setBy("Floor")
                .build();

        LabelledItem labelitem = LabelledItem.newBuilder()
                .setName("Shark")
                .setParent("default")
                .setItem(thisitem)
                .addLabellings(label)
                .build();


        resp.getWriter().print(labelitem.toString());
        resp.setContentType("application/json");
    }
}
