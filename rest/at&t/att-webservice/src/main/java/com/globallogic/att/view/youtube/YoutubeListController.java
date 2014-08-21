package com.globallogic.att.view.youtube;

import com.google.api.services.samples.youtube.cmdline.data.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Miroslav Katrak
 */

@Path("view/youtube")
@Component
@Scope("singleton")
@Transactional
public class YoutubeListController {

    private static final Logger log = LoggerFactory.getLogger(YoutubeListController.class);

    public YoutubeListController() {
        super();
//        log.debug("constructor called");
    }

    @PostConstruct
    public void init() {
        log.debug("init called");
    }


    @GET
    @Path("model")
    @Produces(APPLICATION_JSON)
    @Transactional(readOnly = true)
    public YoutubeListModelResponse getYoutubeListModel() {

        final List<com.google.api.services.youtube.model.SearchResult> items = new Search().search("lenka");

        return YoutubeListModelResponse.newBuilder()
                .items(items)
                .response;
    }

    @GET
    @Path("search")
    @Produces(APPLICATION_JSON)
    @Transactional(readOnly = true)
    public YoutubeListModelResponse getQueryListModel(
            @BeanParam YoutubeListModelRequest request) {

        final String querySearch = request.getQuery();
        if (querySearch == null) {
            throw new WebApplicationException("Missing query.", Response.Status.BAD_REQUEST);
        }

        final List<com.google.api.services.youtube.model.SearchResult> resultsItem = new Search().search(querySearch);

        return YoutubeListModelResponse.newBuilder()
                .items(resultsItem)
                .response;
    }
}

