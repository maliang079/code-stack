package com.ml.test.xxx.mapper.complex;

import java.util.List;

import com.ml.test.xxx.model.complex.NbAlarmDetail;
import com.ml.test.xxx.request.nb.NbAlarmDetailRequest;

import org.apache.ibatis.annotations.Select;

/**
 * Created by mal on 2019/7/9.
 */
public interface NbMapper {

    @Select({
            "<script>                                  ",
            "  select                                  ",
            "    a.id,                                 ",
            "    a.alarm_start_time,                   ",
            "    a.alarm_end_time,                     ",
            "    r.sign_bit_one_remark,                ",
            "    r.sign_bit_tow_remark,                ",
            "    r.sign_bit_three_remark,              ",
            "    r.sign_bit_four_remark,               ",
            "    d.device_name,                        ",
            "    d.device_ins_des,                     ",
            "    p.place_name,                         ",
            "    p.place_detail,                       ",
            "    f.fire_sys_name,                      ",
            "    pj.project_name                       ",
            "  from                                    ",
            "    t_nb_alarm_data a,                    ",
            "    t_nb_report_data r,                   ",
            "    device d,                             ",
            "    place p,                              ",
            "    fire_system f,                        ",
            "    project pj                            ",
            "  where                                   ",
            "    a.alarm_end_time is null AND          ",
            "    a.nb_rd_id = r.id AND                 ",
            "    r.imei = d.device_node_id AND         ",
            "    d.place_id = p.place_id AND           ",
            "    d.fire_sys_code = f.fire_sys_code AND ",
            "    d.project_id = pj.project_id          ",
            "  order by ${orderBy}                     ",
            "</script>                                 "
    })
    List<NbAlarmDetail> selectNbAlarmDetail(NbAlarmDetailRequest req);


}
