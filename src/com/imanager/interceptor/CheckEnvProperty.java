package com.imanager.interceptor;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.framework.service.EnvService;
import com.imanager.login.service.ILoginService;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.ValidationAware;
import com.opensymphony.xwork.interceptor.Interceptor;

public class CheckEnvProperty implements Interceptor {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(CheckEnvProperty.class);
	
	public void destroy() {
	}

	public void init() {
	}

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		
		final Object action = invocation.getAction();
		ValidationAware validation = null;

		if (action instanceof ValidationAware) {
			validation = (ValidationAware) action;
		}
		
		//����û���¼����
		String recordType = EnvService.getValueByProperty(EnvService.RECORD_TYPE);
		if (StringUtils.isBlank(recordType)) {
			validation.addActionError("ϵͳ���󣺻�������\"" + EnvService.RECORD_TYPE + "\"��ֵΪ�գ�");
			return "checkenv";
		}
		
		//����û���¼��
		String recordDomainLoginId = EnvService.getValueByProperty(EnvService.RECORD_DOMAIN_LOGIN_ID);
		if (StringUtils.isBlank(recordDomainLoginId)) {
			validation.addActionError("ϵͳ���󣺻�������\"" + EnvService.RECORD_DOMAIN_LOGIN_ID + "\"��ֵΪ�գ�");
			return "checkenv";
		}
		
		//���cookie��Чʱ��
		String cookieEffectiveTime = EnvService.getValueByProperty(EnvService.COOKIE_EFFECTIVE_TIME);
		if (StringUtils.isBlank(cookieEffectiveTime)) {
			validation.addActionError("ϵͳ���󣺻�������\"" + EnvService.COOKIE_EFFECTIVE_TIME + "\"��ֵΪ�գ�");
			return "checkenv";
		}
		
		//���Ӧ��URL
		String appUrl = EnvService.getValueByProperty(EnvService.APP_URL);
		if (StringUtils.isBlank(appUrl)) {
			validation.addActionError("ϵͳ���󣺻�������\"" + EnvService.APP_URL + "\"��ֵΪ�գ�");
			return "checkenv";
		}
		
		//���ͼƬ����Ŀ¼
		String picDir = EnvService.getValueByProperty(EnvService.PIC_DIR);
		if (StringUtils.isBlank(picDir)) {
			validation.addActionError("ϵͳ���󣺻�������\"" + EnvService.PIC_DIR + "\"��ֵΪ�գ�");
			return "checkenv";
		}
		
		//���ͼƬ����
		String picType = EnvService.getValueByProperty(EnvService.PIC_TYPE);
		if (StringUtils.isBlank(picType)) {
			validation.addActionError("ϵͳ���󣺻�������\"" + EnvService.PIC_TYPE + "\"��ֵΪ�գ�");
			return "checkenv";
		}
		
		//���ͼƬ��С
		String picSize = EnvService.getValueByProperty(EnvService.PIC_SIZE);
		if (StringUtils.isBlank(picSize)) {
			validation.addActionError("ϵͳ���󣺻�������\"" + EnvService.PIC_SIZE + "\"��ֵΪ�գ�");
			return "checkenv";
		}
		
		return invocation.invoke();
	}
	
}