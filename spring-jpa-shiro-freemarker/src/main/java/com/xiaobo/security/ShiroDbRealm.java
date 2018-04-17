package com.xiaobo.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiaobo.bean.SysRole;
import com.xiaobo.bean.SysUserShiro;
import com.xiaobo.service.SysUserShiroService;

public class ShiroDbRealm extends AuthorizingRealm {

	@Autowired
	private SysUserShiroService sysUserShiroService; 
	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		SysUserShiro user = sysUserShiroService.findByUsername(token.getUsername());
		if (user == null || user.getUsername() == null) {
			throw new IncorrectCredentialsException();
		}
		byte[] salt = Encodes.decodeHex(user.getSalt());
		return new SimpleAuthenticationInfo(new ShiroUser(user.getUsername(),user.getUsername(), user.getRoles()
				), user.getPassword(), ByteSource.Util.bytes(salt), getName());
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Encodes.HASH_ALGORITHM);
		matcher.setHashIterations(Encodes.HASH_INTERATIONS);
		// setCredentialsMatcher(new CustomCredentialsMatcher(userBaseService));
		setCredentialsMatcher(new CustomCredentialsMatcher(sysUserShiroService));
	}
;
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		SysUserShiro user = sysUserShiroService.findByUsername(shiroUser.getLoginName());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<SysRole> roles = user.getRoles();
		Set<String> roleList=new HashSet<String>();
		if(roles!=null&&roles.size()>0){
			for(SysRole role:roles){
				 roleList.add(role.getName());
			}
		}
		info.addRoles(roleList);
		//设置权限
		//info.addStringPermissions(permissions);
		return info;
	}

	/**
	 * 自定义存储的user对象，如需携带更多信息，请添加相应属性.
	 */
	public static class ShiroUser implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -3463156268677281735L;

		private String loginName;
		private String realName;
		private List<SysRole> roles;

		public ShiroUser(String loginName, String realName, List<SysRole> roles) {
			this.loginName = loginName;
			this.realName = realName;
			this.roles = roles;
		}
		
		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		public String getRealName() {
			return realName;
		}

		public void setRealName(String realName) {
			this.realName = realName;
		}
 
		public List<SysRole> getRoles() {
			return roles;
		}

		public void setRoles(List<SysRole> roles) {
			this.roles = roles;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hash(loginName);
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ShiroUser other = (ShiroUser) obj;
			if (loginName == null) {
				if (other.loginName != null) {
					return false;
				}
			} else if (!loginName.equals(other.loginName)) {
				return false;
			}
			return true;
		}

	}
}
