 package com.bjsxt.po;

import java.sql.*;
import java.util.*;

public class Nase_wdsslide {

	private Integer id ;
	private String title ;
	private Long order ;
	private String youtube_rel_video ;
	private String image_url ;
	private String link ;
	private String thumb_url ;
	private Long slider_id ;
	private String target_attr_slide ;
	private String published ;
	private String type ;
	private String video_loop ;


	public Integer getId (){
		return id;
	}
	public String getTitle (){
		return title;
	}
	public Long getOrder (){
		return order;
	}
	public String getYoutube_rel_video (){
		return youtube_rel_video;
	}
	public String getImage_url (){
		return image_url;
	}
	public String getLink (){
		return link;
	}
	public String getThumb_url (){
		return thumb_url;
	}
	public Long getSlider_id (){
		return slider_id;
	}
	public String getTarget_attr_slide (){
		return target_attr_slide;
	}
	public String getPublished (){
		return published;
	}
	public String getType (){
		return type;
	}
	public String getVideo_loop (){
		return video_loop;
	}
	public void setId(Integer id ){
		this.id=id;
	}
	public void setTitle(String title ){
		this.title=title;
	}
	public void setOrder(Long order ){
		this.order=order;
	}
	public void setYoutube_rel_video(String youtube_rel_video ){
		this.youtube_rel_video=youtube_rel_video;
	}
	public void setImage_url(String image_url ){
		this.image_url=image_url;
	}
	public void setLink(String link ){
		this.link=link;
	}
	public void setThumb_url(String thumb_url ){
		this.thumb_url=thumb_url;
	}
	public void setSlider_id(Long slider_id ){
		this.slider_id=slider_id;
	}
	public void setTarget_attr_slide(String target_attr_slide ){
		this.target_attr_slide=target_attr_slide;
	}
	public void setPublished(String published ){
		this.published=published;
	}
	public void setType(String type ){
		this.type=type;
	}
	public void setVideo_loop(String video_loop ){
		this.video_loop=video_loop;
	}
}
