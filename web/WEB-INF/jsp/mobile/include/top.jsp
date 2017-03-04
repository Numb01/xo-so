<%-- 
    Document   : top
    Created on : Mar 4, 2016, 2:00:18 PM
    Author     : hanhlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    $(function () {
        hideAll();
    });

    function hideAll() {
        $('.Menu_Left_DV_con').each(function () {
            $(this).hide();
        });
    }

    function hideOfBlock() {
        $('.Menu_Left_DV_con').each(function () {
            var displ = $(this).attr('style');
            if (displ.indexOf('block') > 0) {
                $(this).hide();
            }
        });
    }

    function showSubMenu(id) {
        var displ = $(id).attr('style');
        if (displ.indexOf('none') > 0) {
            hideOfBlock();
            $(id).fadeIn();
            return;
        }
        if (displ.indexOf('block') > 0) {
            $(id).fadeOut();
            return;
        }
    }
</script>
<a href="http://lichvansu.wap.vn/tu-vi-2017.html" rel="nofollow" target="_blank"></a>
<img src="/images/phongthuy_320x50.jpg" alt=""/>
<div class="Header_bg">	
    <a href="/"><img src="images/xsmb-logo.png" style="padding:15px 0 0 0;" /></a>
    <div class="Header_icon_menu">	
        <a href="javascript:void" class="toggle-menu"><img src="images/xo-so-mien-bac-icon-menu.png" class="menu-icon"/></a>
    </div>
    <div class="Header_icon_Lich">	
        <input type="text"  readonly class="From_Lich1" id="datepicker" style="cursor: pointer" />        
    </div>
    <div class="bg_menu-super" style="left: -200px;">
        <div class="menu-super" >                               
            <div class="Menu_Left_Home">
                <a href="/">HOME</a>
            </div>
            <div class="Menu_Left_DV">
                <a href="/xsmb-xo-so-mien-bac.html">XỔ SỐ MIỀN BẮC</a>
            </div>
            <div class="Menu_Left_DV">
                <a href="/xsmt-xo-so-mien-trung.html">XỔ SỐ MIỀN TRUNG</a>
            </div>
            <div class="Menu_Left_DV">
                <a href="/xsmn-xo-so-mien-nam.html">XỔ SỐ MIỀN NAM</a>
            </div>
            <div class="Menu_Left_DV">
                <a href="javascript:showSubMenu('#soicau')">SOI CẦU</a>
            </div>
            <div class="Menu_Left_DV_con" id="soicau">
                <p><a href="/soi-cau-xo-so-mien-bac-xsmb.html">CẦU LOTO</a></p>
                <p><a href="/soi-cau-bach-thu-xo-so-mien-bac-xsmb.html">CẦU LOTO BẠCH THỦ</a></p>
                <p><a href="/soi-cau-xo-so-mien-bac-theo-thu.html">CẦU LOTO THEO THỨ</a></p>
                <p><a href="/soi-cau-xo-so-mien-bac-theo-nhay.html">CẦU LOTO 2 NHÁY</a></p>
                <p><a href="/soi-cau-xo-so-mien-bac-tam-giac.html">CẦU TAM GIÁC</a></p>
            </div>
            <div class="Menu_Left_DV">
                <a href="javascript:showSubMenu('#tkdb')">XSMB THỨ</a>
            </div>
            <div class="Menu_Left_DV_con" id="tkdb">
                <p><a href="/kqsxmb-thu-2.html">Thứ 2</a></p>
                <p><a href="/kqsxmb-thu-3.html">Thứ 3</a></p>
                <p><a href="/kqsxmb-thu-4.html">Thứ 4</a></p>
                <p><a href="/kqsxmb-thu-5.html">Thứ 5</a></p>
                <p><a href="/kqsxmb-thu-6.html">Thứ 6</a></p>
                <p><a href="/kqsxmb-thu-7.html">Thứ 7</a></p>
                <p><a href="/kqsxmb-chu-nhat.html">Chủ Nhật</a></p>
            </div>
        </div>
    </div>
</div>
<div class="both"></div>
<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<!-- ND_320x100 -->
<ins class="adsbygoogle"
     style="display:inline-block;width:320px;height:100px"
     data-ad-client="ca-pub-5650397705160395"
     data-ad-slot="4665121061"></ins>
<script>
    (adsbygoogle = window.adsbygoogle || []).push({});
</script>