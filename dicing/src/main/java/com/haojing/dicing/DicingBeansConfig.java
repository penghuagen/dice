package com.haojing.dicing;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.haojing.common.auth.http.AuthFilter;
import com.haojing.dicing.web.ExceptionHandlerFilter;
import com.haojing.dicing.web.UserLoader;
import com.xiaomishu.web.auth.XmsAuthHttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeweiping on 2020/9/15.
 */
@Configuration
public class DicingBeansConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSource( // @Value("${jdbc.driverClassName}") String driverClassName,
                                  @Value("${spring.datasource.url}") String url,
                                  @Value("${spring.datasource.username}") String username,
                                  @Value("${spring.datasource.password}") String password,
                                  @Value("${dicing.druid.initialSize}") int initialSize,
                                  @Value("${dicing.druid.minIdle}") int minIdle,
                                  @Value("${dicing.druid.maxActive}") int maxActive,
                                  @Value("${dicing.druid.testWhileIdle}") boolean testWhileIdle,
                                  @Value("${dicing.druid.validationQuery}") String validationQuery,
                                  @Value("${dicing.druid.slowSqlMillis}") int slowSqlMillis,
                                  @Value("${dicing.druid.logSlowSql}") boolean logSlowSql) {
        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setValidationQuery(validationQuery);

        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(slowSqlMillis);
        statFilter.setLogSlowSql(logSlowSql);

        dataSource.setProxyFilters(Arrays.asList(statFilter));

        return dataSource;
    }

    @Bean
    public WebSocketConfig customSpringConfigurator() {
        return new WebSocketConfig(); // This is just to get context
    }

    @Bean
    @Lazy(false)
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setName("CorsFilter");
        filterRegistrationBean.setFilter(new Filter() {
            @Override
            public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
                HttpServletRequest request = (HttpServletRequest) req;
                String origin = request.getHeader("Origin");
                if (StringUtils.isNotEmpty(origin) && (origin.endsWith(".4846.com")
                        || origin.contains(".4846.com:"))) {
                    HttpServletResponse response = (HttpServletResponse) res;
                    response.setHeader("Access-Control-Allow-Credentials", "true");
                    response.setHeader("Access-Control-Allow-Origin", origin);
                    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
                    response.setHeader("Access-Control-Max-Age", "3600");
                    response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, If-Modified-Since");
                }
                chain.doFilter(req, res);
            }
        });
        filterRegistrationBean.setUrlPatterns(Arrays.asList(new String[] {"/*"}));

        return filterRegistrationBean;
    }

    @Bean
    @Lazy(false)
    public FilterRegistrationBean exceptionHandlerFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setName("ExceptionHandlerFilter");
        filterRegistrationBean.setFilter(new ExceptionHandlerFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList(new String[] {"/*"}));

        return filterRegistrationBean;
    }

    @Bean
    @Lazy(false)
    public ApplicationContextProvider applicationContextProvider() {
        return new ApplicationContextProvider();
    }

    @Bean
    @DependsOn("applicationContextProvider")
    @Lazy(false)
    public FilterRegistrationBean authFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        Map<String, String> params = new HashMap<>();
        params.put("loaderClass", UserLoader.class.getCanonicalName());
        params.put("authHttpSessionClass", XmsAuthHttpSession.class.getCanonicalName());
        filterRegistrationBean.setInitParameters(params);
        filterRegistrationBean.setName("AuthFilter");
        filterRegistrationBean.setFilter(new AuthFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList(new String[]{"/*"}));

        return filterRegistrationBean;
    }

    @Bean
    @Lazy(false)
    public FilterRegistrationBean wsReqFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setName("wsReqFilter");
        filterRegistrationBean.setFilter(new Filter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                // 创建session
                ((HttpServletRequest) request).getSession(true);
                chain.doFilter(request, response);
            }
        });
        filterRegistrationBean.setUrlPatterns(Arrays.asList(new String[]{"/ws"}));

        return filterRegistrationBean;
    }

//    @Bean(initMethod = "init", destroyMethod = "close")
//    public DataSource dataSource( // @Value("${jdbc.driverClassName}") String driverClassName,
//                                  @Value("${jdbc.url}") String url,
//                                  @Value("${jdbc.username}") String username,
//                                  @Value("${jdbc.password}") String password,
//                                  @Value("${db.pool.initialSize}") int initialSize,
//                                  @Value("${db.pool.minIdle}") int minIdle,
//                                  @Value("${db.pool.maxActive}") int maxActive,
//                                  @Value("${db.slowSqlMillis}") int slowSqlMillis,
//                                  @Value("${db.logSlowSql}") boolean logSlowSql) {
//        DruidDataSource dataSource = new DruidDataSource();
////        dataSource.setDriverClassName(driverClassName);
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setInitialSize(initialSize);
//        dataSource.setMinIdle(minIdle);
//        dataSource.setMaxActive(maxActive);
//
//        StatFilter statFilter = new StatFilter();
//        statFilter.setSlowSqlMillis(slowSqlMillis);
//        statFilter.setLogSlowSql(logSlowSql);
//
//        dataSource.setProxyFilters(Arrays.asList(statFilter));
//
//        return dataSource;
//    }
}
