package com.test.testpro.model;

import com.test.testpro.service.BeanUtil;
import lombok.*;
import org.aspectj.weaver.AjAttribute;
import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor

public class Comment extends Auditable {
    @Id
    @GeneratedValue

    private Long id;
    @NonNull
    private String body;
    //link
    @ManyToOne
    @NonNull
    private Link link;


    public String getPrettyTime() {
        PrettyTime pt = BeanUtil.getBean(PrettyTime.class);
        return pt.format(convertToDateViaInstant(getCreationDate()));
    }

    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }

}
