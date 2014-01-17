package builder;
 
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
 
public class LiveGroupRenderer implements RowRenderer {
 
    public void render(final Row row,final  java.lang.Object data) {
        if(data instanceof String[]) {
            String[] ary = (String[]) data;
            Div div = new Div();
            Image icon = new Image();
            icon.setStyle("padding: 0px 10px");
            icon.setSrc("/img/Centigrade-Widget-Icons/EnvelopeOpen-16x16.png");
            div.appendChild(icon);
            new Label(ary[0]).setParent(div);
            row.appendChild(div);
            new Label(ary[1]).setParent(row);
            new Label(ary[2]).setParent(row);
            new Label(ary[3]).setParent(row);
        } else {
            new Label(data.toString()).setParent(row);
        }
    }
     
}
