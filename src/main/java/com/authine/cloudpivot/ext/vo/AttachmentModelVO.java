package com.authine.cloudpivot.ext.vo;

import com.authine.cloudpivot.engine.api.model.runtime.AttachmentModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentModelVO extends AttachmentModel {
    private String parentSchemaCode;
    private String parentBizObjectId;
    private String bizObjectId;
    private String bizPropertyCode;
}
