package com.mycompany.entityclasses;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-01T13:41:58")
@StaticMetamodel(PublicVideo.class)
public class PublicVideo_ { 

    public static volatile SingularAttribute<PublicVideo, String> duration;
    public static volatile SingularAttribute<PublicVideo, Date> datePublished;
    public static volatile SingularAttribute<PublicVideo, String> youtubeVideoId;
    public static volatile SingularAttribute<PublicVideo, String> description;
    public static volatile SingularAttribute<PublicVideo, Integer> id;
    public static volatile SingularAttribute<PublicVideo, String> title;
    public static volatile SingularAttribute<PublicVideo, String> category;

}