package com.incentro.taggert.api.v1b1;

        import com.google.inject.Singleton;
        import com.incentro.taggert.v1b1.Item;
        import com.incentro.taggert.v1b1.LabelledItem;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.Arrays;
        import java.util.List;

        import java.util.Random;


@Singleton
public class ItemsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> values = returnValues();

        LabelledItem labelitem = makeItem(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5));

        resp.getWriter().print(labelitem.toString());
        resp.setContentType("application/json");
    }

    List<String> returnValues () {

        List<String> id = Arrays.asList("default", "other", "bla1", "bla2");
        List<String> url = Arrays.asList("http://google.nl", "http://wikipedia.com", "http://jsiojaio.nl", "http:ijsiojois.nl");
        List<String> by = Arrays.asList("Floor", "Hayo", "Ted", "Doris");
        List<String> label = Arrays.asList("shark", "rainbow shark", "cat", "dog");
        List<String> name = Arrays.asList("img1", "img2", "img3", "img4");
        List<String> parent = Arrays.asList("default", "dataset1", "dataset2", "dataset3");

        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(id.size());

        return Arrays.asList(id.get(randomNumber), url.get(randomNumber), by.get(randomNumber), label.get(randomNumber), name.get(randomNumber), parent.get(randomNumber));
    }

    protected LabelledItem makeItem(String id, String url, String by, String thislabel, String name, String parent) {

        Item.ImageLink link = Item.ImageLink.newBuilder()
                .setId(id)
                .setUrl(url)
                .build();

        Item item = com.incentro.taggert.v1b1.Item.newBuilder()
                .setName(name)
                .setImageLink(link)
                .build();

        LabelledItem.Labelling label = LabelledItem.Labelling.newBuilder()
                .addLabels(thislabel)
                .setBy(by)
                .build();


        return LabelledItem.newBuilder()
                .setName(name)
                .setParent(parent)
                .setItem(item)
                .addLabellings(label)
                .build();
    }
}
