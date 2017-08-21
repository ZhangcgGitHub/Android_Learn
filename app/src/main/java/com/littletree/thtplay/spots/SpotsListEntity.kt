package com.littletree.thtplay.spots

import com.littletree.thtplay.util.BaseEntity
import com.littletree.thtplay.util.Comment

/**
 * Created by frank.zhang on 2017/8/18.
 */
class SpotsListEntity : BaseEntity() {

    @Comment("分页页数")
    var total_pages: Int = 0

    @Comment("每页的记录数")
    var page_size: Int = 0

    @Comment("分页的页数(页码)")
    var page_num: Int = 0

    @Comment("项目记录")
    var records: MutableList<Spots> = arrayListOf()

    data class Spots(
            @Comment("景点Id") val spots_id: Long,
            @Comment("景点详情对象 Detail") val details: Detail,
            @Comment("景点名称") val name: String,
            @Comment("景点二维码Code") val code: String
    )

    data class Detail(
            @Comment("景点名称") val tour_name: String,
            @Comment("景点简介") val tour_summary: String,
            @Comment("景点详情") val tour_detail: String,
            @Comment("景点列表Icon") val list_pic: String,
            @Comment("景点列表Icon name") val list_pic_name: String,
            @Comment("景点详情图片") val detail_pic: String,
            @Comment("景点详情图片名称") val detail_pic_name: String,
            @Comment("景点详情中音频") val audio_url: String,
            @Comment("景点详情中音频名称") val audio_name: String,
            @Comment("景点详情中视频") val video_url: String,
            @Comment("景点详情中视频名称") val video_name: String,
            @Comment("") val isaudio: Boolean,
            @Comment("") val isvideo: Boolean
    )

}