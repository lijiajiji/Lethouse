<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">

<script language="JavaScript" src="../admin/js/jquery-1.8.3.js"></script>
  <script language="JavaScript">
    $(function () {
        //加载异步类型
        $.post("getTypes",null,function (data) {
            for (var i=0;i<data.length;i++) {
                var option=$("<option value="+data[i].id+">"+data[i].name+"</option>");
                $("#typeid").append(option);
            }
            //设置下拉框回显
            if (${"condition.tid!=null"}){
                $("#typeid").val(${codition.tid});
            }
        },"json");


        //加载异步类型
        $.post("getDistricts",null,function (data) {
            for (var i = 0; i < data.length; i++) {
                var option = $("<option value=" + data[i].id + ">" + data[i].name + "</option>");
                $("#districtid").append(option);
            }

            if (${condition.did!=null}){
                $("#districtid").val(${codition.did});
            }
        },"json");

        //给区域添加改变事件，显示对应的街道
        $("#districtid").change(function () {
            var did=$(this).val();
            //发送异步请求获取街道
            showStreet(did);
        });

    });

    //显示街道
    function showStreet(did) {
        $.post("GetStreetByDid2",{"did":did},function (data) {
            $("#streetId>option").remove();
            for (var i=0;i<=data.length;i++ ){
                //创建一个dom节点
                var option=$("<option value="+data[i].id+">"+data[i].name+"</option>");
                $("#streetId").append(option);//追加节点
            }

            if(${condition.sid!=null}){
                $("#streetId").val(${codition.sid});
            }

        },"json");

    }

    //去分页
    function goPage(num){
        $("#showPage").val(num);
        //js提交表单 等价于手动点击提交按钮
        $("#form").submit();
    }



  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  <FORM id=form method=post action=goList>
      <!--页码-->
      <input type="text" id="showPage" value="3" name="page">
  <DT>
  <UL>
    <LI class=bold>房屋信息</LI>
    <LI>标题：<INPUT class=text type=text name="title" value="${codition.title}">
    </LI></UL></DT>
  <DD>
  <UL>
    <LI class=first>价格 </LI>
    <LI>
      开始价格：<input  type="text" name="startPrice" value="${codition.startPrice}" style="width: 30px;">
        结束价格：<input  type="text" name="andPrice" value="${codition.endPrice}" style="width: 30px;">
    </LI>
    </UL></DD>
  <DD>
  <UL>
    <LI class=first>区域</LI>
    <LI><SELECT id=districtid name=did> <OPTION selected
      value="">请选择</OPTION>
    </SELECT>
    <LI class=first>街道</LI>
    <LI><SELECT id=streetId name=did>
      <OPTION selected value="">请选择</OPTION>
    </SELECT>
    </LI></UL></DD>
  <DD>
  <UL>
    <LI class=first>类型 </LI>
    <LI><SELECT id="typeid" name=tid>
      <OPTION selected value="">请选择</OPTION>
    </SELECT> </LI></UL></DD>
 <DD><UL>
    <DD>
      <UL>
        <LI class=first>面积</LI>
        <LI>
            <SELECT id="flooa" name=flooa>
                <OPTION selected value="">请选择</OPTION>
                <OPTION value="0-40">40以下</OPTION>
                <OPTION value="41-80">41-80</OPTION>
                <OPTION value="81-500">81-500</OPTION>
            </SELECT>
            <script language="JavaScript">
                $("#flooa").val(${codition.flooa})
            </script>
        </LI></UL></DD>
    <DD><UL>
   <LI CLASS="first">搜索</LI>
    <LABEL class=ui-blue><INPUT value=搜索房屋 type=submit name=search></LABEL>
    </UL></DD>
  </FORM></DL></DIV>
<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${pageInfo.list}" var="h">
      <TR>
          <TD class=house-thumb><span><A href="details.jsp" target="_blank"><img src="http://localhost:81/${h.path}" width="100" height="75" alt=""></a></span></TD>
          <TD>
              <DL>
                  <DT><A href="details.jsp" target="_blank">${h.title}</A></DT>
                  <DD>${h.dname}=${h.sname},${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
          <TD class=house-type>${h.tname}</TD>
          <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD>
      </TR>
  </c:forEach>
  </TBODY></TABLE>
<DIV class=pager>
<UL>
    <LI class=current><A href="javascript:goPage(1);">首页</A></LI>
    <LI><A href="javascript:goPage(${pageInfo.prePage==0?1:pageInfo.prePage});">上一页</A></LI>
    <LI><A href="javascript:goPage(${pageInfo.nextPage==0?pageInfo.pages:pageInfo.nextPage});">下一页</A></LI>
    <LI><A href="javascript:goPage(${pageInfo.pages});">末页</A></LI></UL>
    <SPAN class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN>
</DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
