package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.*;
import com.longfor.itserver.common.util.DateUtil;
import com.longfor.itserver.common.util.StringUtil;
import com.longfor.itserver.common.vo.programBpm.common.ApplyCreateResultVo;
import com.longfor.itserver.common.vo.programBpm.common.ApplySubmitResultVo;
import com.longfor.itserver.common.vo.programBpm.common.FileVo;
import com.longfor.itserver.entity.*;
import com.longfor.itserver.esi.bpm.ProgramBpmUtil;
import com.longfor.itserver.mapper.*;
import com.longfor.itserver.service.IProgramFileService;
import com.longfor.itserver.service.IProgramService;
import com.longfor.itserver.service.base.AdminBaseService;
import com.longfor.itserver.service.util.AccountUitl;
import net.mayee.commons.TimeUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author wax Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("ProgramFileService")
public class ProgramFileServiceImpl extends AdminBaseService< ProgramFile> implements IProgramFileService {

}

