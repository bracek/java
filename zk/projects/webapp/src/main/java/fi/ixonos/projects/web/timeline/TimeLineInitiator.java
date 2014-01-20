package fi.ixonos.projects.web.timeline;

import java.util.Iterator;

import org.zkforge.timeline.Bandinfo;
import org.zkforge.timeline.data.OccurEvent;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;

public class TimeLineInitiator extends AnnotateDataBinderInit {

    @SuppressWarnings("unchecked")
    @Override
    public void doAfterCompose(final Page page,
final  Component[] comps) throws Exception {
        getTimelineWindow(comps).init();
        super.doAfterCompose(page, comps);
    }

    private TimeLineWindow getTimelineWindow(final Component[] comps) throws Exception {
        for (Component comp : comps) {
            if (comp instanceof TimeLineWindow) {
                return (TimeLineWindow) comp;
            }
        }
        throw new Exception("TimeLineWindow not found!");
    }
}
