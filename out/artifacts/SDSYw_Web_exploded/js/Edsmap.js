var _PopLayer = null;
var _MapFinished = false;
var _state = false;
var _EdsMapServer = 'http://dw.edushi.com/';
var cdata = 'http://cdata.edushi.com';
var charset = 'utf-8';
var zoom = 0;
var switchCity = 'chengdu';
var vM= null;

function ENetwork() { };
ENetwork.GetExecutionID = function() {
    var a = new Date, b = Date.UTC(a.getFullYear(), a.getMonth(), a.getDate(), a.getHours(), a.getMinutes(), a.getSeconds(), a.getMilliseconds());
    b += Math.round(Math.random() * 1000000);
    return b
};
ENetwork.DownloadScriptCallback = function(a) {
    if (a) {
        a();
    }
};
ENetwork.DownloadScript = function(a, b, c) {
    try {
        if (a == null || a == "undefined" || a.length == 0) {
            throw new ENetworkException("ENetwork:DownloadScript", "err_noscripturl", l24ht);
        }
        var elScript = document.createElement("script");
        elScript.type = "text/javascript";
        elScript.language = "javascript";
        elScript.id = typeof (c) == "undefined" ? ENetwork.GetExecutionID() : c;
        elScript.src = a;
        // elScript.charset = charset;
        if (document.getElementById(c)) {
            ENetwork.GetAttachTarget().removeChild(document.getElementById(c));
        }
        ENetwork.GetAttachTarget().appendChild(elScript);
        if (navigator.userAgent.indexOf("IE") >= 0) {
            elScript.onreadystatechange = function() {
                if (elScript && ("loaded" == elScript.readyState || "complete" == elScript.readyState)) {
                    elScript.onreadystatechange = null;
                    ENetwork.DownloadScriptCallback(b);
                }
            }
        }
        else {
            elScript.onload = function() {
                elScript.onload = null;
                ENetwork.DownloadScriptCallback(b);
            }
        }
        return elScript.id;
    }
    catch (e) {
        alert('加载失败！');
    }
};
ENetwork.GetAttachTarget = function() {
    if (document.getElementsByTagName("head")[0] != null) {
        return document.getElementsByTagName("head")[0];
    }
    else {
        throw new ENetworkException("ENetwork:cstr", "err_noheadelement", l611ft);
    }
};
function ENetworkException(b, c, a) {
    this.source = b;
    this.name = c;
    this.message = a;
}
ENetworkException.prototype.Name = this.name;
ENetworkException.prototype.Source = this.source;
ENetworkException.prototype.Message = this.message;


window.Load(_EdsMap);
var cityCode = 'hz';
function Load(eds) {
    var x = 0, y = 0;
    _EdsMap = eds;
    // charset = 'utf-8';
    var v = 0;
    var s = false;
    cityCode = eds.CityCode;

    //当城市编等于“chengdu”时，显示二维、卫星切换功能
    if (cityCheckIn(eds.CityCode)) {
        s = true;
    }
    if (eds) {

        if (eds.Height < 258) {
            eds.Height = 258;
        }
        var _Height = eds.Height - 80;
        if (eds.Width < 200) {
            eds.Width = 200;
        }

        if (typeof eds.Charset != 'undefined') {
            if (eds.Charset.length > 0 && (eds.Charset.toLowerCase() == 'utf-8' || eds.Charset.toLowerCase() == 'gb2312')) {
                charset = eds.Charset;
                if (eds.Charset.toLowerCase() == 'gb2312') {
                    v = 1;
                }
            }
        }

        //        if (typeof eds.ShowName != 'undefined' && typeof eds.ShowAddress != 'undefined') {
        //            var curl = 'http://dw.edushi.com/GetCommanyOidOrCid.aspx?CityCode=' + eds.CityCode + '&CompanyName=' + escape(eds.ShowName) + '&address=' + escape(eds.ShowAddress) + '&e=' + charset;
        //            ENetwork.DownloadScript(curl, function() {
        //                if (typeof _Cmp != 'undefined') {
        //                    var param = '';
        //                    if (_Cmp.Cid != "0") {
        //                        param = '?cid=' + _Cmp.Cid;
        //                    } else if (_Cmp.Oid != "0") {
        //                        param = '?oid=' + _Cmp.Oid;
        //                    }
        //                    if (document.getElementById("bigMap") != null) {
        //                        document.getElementById("bigMap").setAttribute("href", "");
        //                        document.getElementById("bigMap").setAttribute("href", "http://" + eds.CityCode + ".edushi.com" + param);
        //                    }
        //                }
        //            });
        //        }

        //<div style="position:absolute; right:10px; top:10px;z-index:1000; text-align:center; line-height:20px;"><a href="http://' + cityCode + '.edushi.com" id="bigMap" target="_blank"><img src="' + _EdsMapServer + 'images/btna.jpg" style="border:0px;"  alt="" /></a></div>

        var lookBigMap = '<div style="position:absolute; z-index:1000; bottom:0px;left:0px;" ><a href="http://' + cityCode + '.edushi.com" id="logo" style=" width:105px;height:48px; background:url(' + _EdsMapServer + 'images/logo2.png) no-repeat 0 0; display:block;_background:url(' + _EdsMapServer + 'images/logo1.png) no-repeat 0 0;"  target="_blank"></a></div>';
        if (eds.Height < 270) {
            lookBigMap += '<div style="position: absolute; z-index: 1000; top: 5px; left: 5px;"><a href="javascript:void(0);" onclick="AddZoom()" title="放大" ><img src="' + _EdsMapServer + 'images/udAdd.jpg" alt="放大" style="border:0px;" /></a><br /><a href="javascript:void(0);" onclick="ReduceZoom()" title="缩小"><img src="' + _EdsMapServer + 'images/udReduce.jpg" alt="缩小" style="border:0px;" /></a></div>';
        }
        if (eds.Width >= 400) {
            lookBigMap += '<div style="position:absolute; z-index:1000;color:Blue;font-size:12px;background:#fff;filter:alpha(opacity=60); bottom:5px;left:105px;">版权所有@2004-2011 Edushi 出版号:ISBN:7-900200-28-2</div>';
        }

        if (document.getElementById("EdushiMap") == null) {
            var showMapHtml = '<div style=" position:relative; width:' + eds.Width + 'px;heigth:' + _Height + ' "><div id="EdushiMap"></div>' + lookBigMap + '</div>';
            document.write(showMapHtml);
        }

        if (typeof eds.CX == 'undefined' || typeof eds.CY == 'undefined') {
            var tUrl = 'http://dw.edushi.com/GetPoint.aspx?CityCode=' + eds.CityCode + '&CompanyName=' + escape(eds.ShowName) + '&address=' + escape(eds.ShowAddress) + '&e=' + charset;
            ENetwork.DownloadScript(tUrl, function() {
                if (typeof _Company != 'undefined') {
                    x = _Company.X;
                    y = _Company.Y;
                    eds.CX = x;
                    eds.CY = y;
                    //http://api4.edushi.com/default.aspx?MapID=EdushiMap&City=hz&L=zh-chs&x=12867&y=31462&w=950&h=394&eye=true&ew=178&eh=138&e=utf-8&z=1&v=0&zb=true&s=true&solution=default

                    //  var apiURL = 'http://capi3.edushi.com/?City=' + eds.CityCode + '&L=zh-chs&MapID=EdushiMap&x=' + x + '&y=' + y + '&w=' + eds.Width + '&h=' + _Height + '&eye=0&e=' + charset + '&z=2&v=0&s=false&m=false';  //s=false表示隐藏地图切换

                    var apiURL = 'http://capi4.edushi.com/default.aspx?MapID=EdushiMap&City=' + eds.CityCode + '&L=zh-chs&x=' + x + '&y=' + y + '&w=' + eds.Width + '&h=' + _Height + '&eye=0&e=' + charset + '&z=2&v=' + v + '&zb=true&s=' + s + '&solution=default&m=false';

                    ENetwork.DownloadScript(apiURL, function() {
                        EdsMapLoadComplete();
                    });
                }
            });
        } else {
            x = eds.CX;
            y = eds.CY;
            //var apiURL = 'http://capi3.edushi.com/?City=' + eds.CityCode + '&L=zh-chs&MapID=EdushiMap&x=' + x + '&y=' + y + '&w=' + eds.Width + '&h=' + _Height + '&eye=0&e=' + charset + '&z=2&v=0&s=false&m=false';

            var apiURL = 'http://capi4.edushi.com/default.aspx?MapID=EdushiMap&City=' + eds.CityCode + '&L=zh-chs&x=' + x + '&y=' + y + '&w=' + eds.Width + '&h=' + _Height + '&eye=0&e=' + charset + '&z=2&v=' + v + '&zb=true&s=' + s + '&solution=default&m=false';
            ENetwork.DownloadScript(apiURL, function() {
                EdsMapLoadComplete();
            });
        }
    };
}

function EdsMapCallBack() {

};
function EdsMapLoadComplete() {
    vM = null;
    if (typeof vEdushiMap == 'undefined') {
        setTimeout('EdsMapLoadComplete()', 1000);
    }
    else {
        vM = vEdushiMap;
        vEdushiMap.attachEvent(AlaMap.KeyWord.EventName.MapMouseUp, function(e) {
            if (_state) {
                fnShowPop();
            }
        });

        if (_EdsMap.Width > 320) {
            //点击实休弹出泡泡
            vEdushiMap.attachEvent(AlaMap.KeyWord.EventName.SpotClick, function(spot) {
                // alert(spot.Name+","+spot.Address);
                var oID = spot.ID;
                showEntityPop(oID);
            });
        }
        if (_EdsMap.LoadCompleteCallBack) {

            _EdsMap.LoadCompleteCallBack();
        }
        _MapFinished = true;
        
        var css = vEdushiMap.$C('link');
        css.rel = 'stylesheet';
        css.type = 'text/css';
        css.href = _EdsMapServer + 'css/style.css';
        vEdushiMap.ContentWindow.document.getElementsByTagName('head')[0].appendChild(css);
        
        //当城市编等于“chengdu”时，显示二维、卫星切换功能
        if (cityCheckIn(_EdsMap.CityCode)) {
            if (vM.Property.flgShowSogouMap) {
                vM.LoadPlug('SogouMap');
            }
            vM.attachEvent(AlaMap.KeyWord.EventName.Switch3D, function() {
                fnInitDelay();
            });
            vM.attachEvent(AlaMap.KeyWord.EventName.Switch2D, function() {
                fnInitDelay();
            });
            vM.attachEvent(AlaMap.KeyWord.EventName.SwitchWX, function() {
                fnInitDelay();
            });
        }
        
        zoom = vEdushiMap.Zoom();
        // alert(zoom);

        var obj = vEdushiMap.$('ZoomBarControl');  //滑杆
        if (obj) {
            //当城市编等于“chengdu”时，显示默认滑杆
            if (!cityCheckIn(_EdsMap.CityCode)) {
                if (_EdsMap.Height < 270) {
                    obj.style.display = 'none';
                } else {
                    obj.style.backgroundImage = "url('" + _EdsMapServer + "images/mapcontrols.gif')";
                }
            }
        }

        // if (cityCode == 'hz' || cityCode == "hangzhou") {
        //ShowCompanyPop(_EdsMap);
        daoXiang(_EdsMap);
        // }
        vEdushiMap.ViewPlots(false, 'ad', true);
        vEdushiMap.ViewSigns(false, 'way');
    }
};


function fnInitDelay() {
    if (vM.MapState.Succeed) {//判断google地图是否加载成功
        setTimeout(function() {
        vM.MoveTo(vM.CenterX() + 1, vM.CenterY());
        }, 100);
    }
    else {
        setTimeout("fnInitDelay()", 300);
        return;
    }
}


//放大
function AddZoom() {
    zoom--;
    if (zoom < 0) {
        zoom = 0;
    }
    vEdushiMap.FlatZoom(zoom);
}
//缩小
function ReduceZoom() {
    zoom++;
    if (zoom > 3) {
        zoom = 3;
    }
    vEdushiMap.FlatZoom(zoom);
}

function $Rnd(flg) {
    var d, s = '';
    if (!flg) flg = 100000;
    d = new Date();
    s += d.getHours();
    s += d.getMinutes();
    s += d.getSeconds();
    s += d.getMilliseconds();
    return Math.round(Math.random() * flg).toString() + s.toString();
};
function _GetExecutionID() {
    var a = new Date, b = Date.UTC(a.getFullYear(), a.getMonth(), a.getDate(), a.getHours(), a.getMinutes(), a.getSeconds(), a.getMilliseconds());
    b += Math.round(Math.random() * 1000000);
    return b
};

function ShowPopInfo(o) {
    //  Load(o);
    if (o) {
        ShowCompanyPop(o);
    }
}
var _EdsPopInfoData = null;
function ShowCompanyPop(o) {
    if (typeof o.ShowName != "undefined" && o.ShowAddress != "undefined") {
        if (!_MapFinished) {
            return;
        }
        if (o != null) {
            _EdsPopInfoData = o;
            if (o.CX == '' || o.CY == '') {
                return;
            }
            _PopLayer = null;
            if (_PopLayer == null) {
                _PopLayer = vEdushiMap.NewMapLayer('Pop', 269, true);
            }
            _PopLayer.innerHTML = '';

            var cityCode = o.CityCode;
            if (cityCode != "" && cityCode != null) {
                var strHTML = '<div class="close"><a href="javascript:void(0);" ><img src="' + _EdsMapServer + 'images/close_btn.jpg" onclick="parent.ClosePaoPao()" alt="关闭" /></a></div><div class="text"><ul><li><span></span>' + o.ShowName + '</li><li><span></span>' + o.ShowAddress + '</li><li class="li_s"><span>周边服务：</span></li><li>';

                var menuUrl = _EdsMapServer + 'EdsMenu.aspx?cityCode=' + o.CityCode + '&e=' + charset;
                ENetwork.DownloadScript(menuUrl, function() {
                    if (typeof _MenuInfo != 'undefined') {
                        strHTML += '<a href="' + _MenuInfo.mapUrl + '" target="_blank">查公交</a> ';
                        if (_MenuInfo.jobUrl != '') {
                            strHTML += '<a href="' + _MenuInfo.jobUrl + '" target="_blank" >找工作</a> ';
                        }
                        if (_MenuInfo.hotelUrl != '') {
                            strHTML += '<a href="' + _MenuInfo.hotelUrl + '" target="_blank">订酒店</a> ';
                        }
                        if (_MenuInfo.discountUrl != '') {
                            strHTML += '<a href="' + _MenuInfo.discountUrl + '" target="_blank">搜折扣</a>';
                        }
                        if (_MenuInfo.houseUrl != '') {
                            strHTML += '<a href="' + _MenuInfo.houseUrl + '" target="_blank"> 买房子</a>';
                        }
                        strHTML += "</li></ul></div>";
                    }
                    else {
                        strHTML += "暂时无周边服务！</li></ul></div>";
                    }

                    var pop = vEdushiMap.$('paopaoInfo');
                    if (!pop) {
                        pop = vEdushiMap.$C('div');
                        pop.id = 'paopaoInfo';
                    }
                    var vM = vEdushiMap;
                    pop.className = 'ys_t';

                    pop.innerHTML += strHTML;

                    vEdushiMap.MoveTo(o.CX * 1 + vM.GradeWin2EGIS(150), o.CY * 1 - vM.GradeWin2EGIS(30), true);
                    vEdushiMap.AppendEntity(pop, _PopLayer, false, o.CX * 1, o.CY * 1, 352, 210, 0, 80, false);
                });
                // }
            }
        }
    }
}

function daoXiang(o) {
    if (typeof o.ShowName != 'undefined' && o.ShowAddress != 'undefined') {
        if (!_MapFinished) {
            return;
        }

        if (o != null) {
            _EdsPopInfoData = o;
            if (o.CX == '' || o.CY == '') {
                return;
            }
            _PopLayer = null;
            if (_PopLayer == null) {
                _PopLayer = vEdushiMap.NewMapLayer('Pop', 269, true);
            }
            _PopLayer.innerHTML = '';
            var cityCode = o.CityCode;
            if (cityCode != "" && cityCode != null) {
                var curl = 'http://dw.edushi.com/GetCommanyOidOrCid.aspx?CityCode=' + o.CityCode + '&CompanyName=' + escape(o.ShowName) + '&address=' + escape(o.ShowAddress) + '&e=' + charset;
                ENetwork.DownloadScript(curl, function() {
                    if (typeof _Cmp != 'undefined') {
                        var param = '';
                        if (_Cmp.Cid != "0") {
                            param = '?cid=' + _Cmp.Cid;
                        } else if (_Cmp.Oid != "0") {
                            param = '?oid=' + _Cmp.Oid;
                        }
                        var strHTML = '<div style="height:28px;z-index:1;min-width:10px;width:auto !important;width:10"><div style="height:19px; padding-top:2px; padding-left:5px; padding-right:5px; white-space:nowrap;border:1px #e3e3e3 solid;background-color:ff7000;color:#1A70C7; font-size:12px;font-weight:bolder;"><a href="{Url}" id="bigMap" style="color:#FFFFFF; font-size:12px; text-decoration:none;" title="{Title}" target="{Target}">{Name}</a></div><div style="float:left; margin-left:0px;margin-top:-2px; font-size:10px; width:1px; height:10px;z-index:2"><img src="' + AlaMap.GetScriptLocation() + '/Images/DaoXiang.gif" border="0"></div></div>';
                        strHTML = strHTML.replace('{Target}', '_blank').replace('{Name}', SubStr(o.ShowName, 8)).replace('{Title}', o.ShowName).replace("{Url}", "http://" + o.CityCode + ".edushi.com" + param);

                        var pop = vEdushiMap.$('daoxiang');
                        if (!pop) {
                            pop = vEdushiMap.$C('div');
                            pop.id = 'daoxiang';
                        }
                        var vM = vEdushiMap;
                        pop.innerHTML += strHTML;

                        vEdushiMap.MoveTo(o.CX * 1 + vM.GradeWin2EGIS(67), o.CY * 1, true);
                        vEdushiMap.AppendEntity(pop, _PopLayer, false, o.CX * 1, o.CY * 1, false, 10, 0, 30, false);
                    }

                });
            }
        }
    }
}

function showEntityPop(oID) {
    ClosePaoPao();
    CloasEntityPaoPao();
    var css = vEdushiMap.$C('link');
    css.rel = 'stylesheet';
    css.type = 'text/css';
    css.href = _EdsMapServer + 'css/entityPaoPao.css';
    vEdushiMap.ContentWindow.document.getElementsByTagName('head')[0].appendChild(css);

    if (oID != 0) {
        var pop = vEdushiMap.$('paoPaoInfo');
        if (!pop) {
            pop = vEdushiMap.$C('div');
            pop.id = 'paoPaoInfo';
        }
        pop.className = 'ysa_3';
        var url = cdata + "/SearchData/SearchMapByOwner/" + _EdsMap.CityCode + "/zh-chs/Format/Json/Search?q=" + oID + "&e=" + charset;
        ENetwork.DownloadScript(url, function() {
            if (typeof _EntityInfo != "undefined" && _EntityInfo != "" && _EntityInfo != null) {
                _PopLayer = null;
                if (_PopLayer == null) {
                    _PopLayer = vEdushiMap.NewMapLayer('Pop', 269, true);
                }
                _PopLayer.innerHTML = '';
                var imgpath = '';
                var address = _EntityInfo[0].Address.substring(0, 14);

                if (_EntityInfo[0].ImgName == "" || _EntityInfo[0].ImgName == null) {
                    imgpath = _EdsMapServer + 'images/nophoto.jpg';
                }
                else {
                    imgpath = 'http://img.Edushi.com/userupImages/pic_' + _EdsMap.CityCode + '/' + _EntityInfo[0].ImgPath + '/' + _EntityInfo[0].ImgName + '';
                }
                //_EntityInfo[0].OCName
                //var strHTML = '<div class="title"><span>' + _EntityInfo[0].OCName + '</span><div class="close2"><a href="javascript:void(0);"  onclick="parent.CloasEntityPaoPao()"><img src="' + _EdsMapServer + 'images/close.png" alt="关闭" /></a></div></div><div class="clear"></div><dl><dt><a href="http://' + _EdsMap.CityCode + '.edushi.com/hy/2-' + oID + '.shtml" target="_blank"><img src="' + imgpath + '" width="96" height="75"  /></a></dt><dd><p>' + address + '</p><p>已收录' + _EntityInfo[0].TotalCompanyCount + '家企业</p><p><a href="http://' + _EdsMap.CityCode + '.edushi.com/hy/2-' + oID + '.shtml" target="_blank">查看详情>></a></p></dd></dl>';

                var strHTML = '<div class="title"><div class="left"><a href="javascript:void(0);" title="' + _EntityInfo[0].OCName + '">' + SubStr(_EntityInfo[0].OCName, 14) + '</a></div><div class="right"><a href="javascript:void(0);"  onclick="parent.CloasEntityPaoPao()"><img src="' + _EdsMapServer + 'images/s_tright_Close.jpg" border="none" /></a></div></div><div class="AandS"><div class="left"><a href="javascript:void(0);" title="' + _EntityInfo[0].Address + '">' + SubStr(address, 10) + '</a></div><div class="right">(已收录<span>' + _EntityInfo[0].TotalCompanyCount + '</span>家企业)</div></div><div class="clear"></div><div class="btn2"><div class="star"><a href="http://' + _EdsMap.CityCode + '.edushi.com/hy/2-' + oID + '.shtml" target="_blank">查看详情</a></div><div class="fd"><a href="http://' + _EdsMap.CityCode + '.edushi.com?oid=' + oID + '" target="_blank">查看大图</a></div></div><div class="clear"></div><div class="zb"><span>周边查询：</span><a href="http://' + _EdsMap.CityCode + '.edushi.com?oid=' + oID + '" target="_blank">公交</a>  <a href="http://' + _EdsMap.CityCode + '.edushi.com/jd/?oid=' + oID + '" target="_blank">酒店</a></div>';

                pop.innerHTML = strHTML;
                vEdushiMap.MoveTo(_EntityInfo[0].X * 1 + vEdushiMap.GradeWin2EGIS(150), _EntityInfo[0].Y * 1 - vEdushiMap.GradeWin2EGIS(0), true);
                vEdushiMap.AppendEntity(pop, _PopLayer, false, _EntityInfo[0].X * 1, _EntityInfo[0].Y * 1, 352, 210, 0, 80, false);
            }
        });
    }
}


function txtFocus(obj) {
    //    if (obj.value == '请输入起点' || obj.value == '请输入目的地') {
    //        obj.value = '';
    //    }
}
function _EdsChangeSearch(type, sType) {
    if (vEdushiMap) {
        var vM = vEdushiMap;
        if (type == 0) {
            if (vM.$('paopao_search')) {
                vM.$('paopao_search').style.display = 'none';
                vM.$('paopao_search_2').style.display = '';
            }
        }
        else {


            if (vM.$('paopao_search')) {
                vM.$('paopao_search').style.display = '';
                vM.$('paopao_search_2').style.display = 'none';
                if (typeof sType != 'undefined') {
                    //alert(sType);
                    _EdsSearchType = sType;
                    if (sType == 0) {

                        vEdushiMap.$('txtPopSearchInput').value = '请输入起点';
                    }
                    else {
                        vEdushiMap.$('txtPopSearchInput').value = '请输入目的地';
                    }
                }
            }
        }
    }
};
function _EdsSearchInterface(sType) {
    if (sType) {
        _EdsSearchType = sType;
    }
    if (_EdsPopInfoData != null) {
        var sValue = vEdushiMap.$('txtPopSearchInput').value;
        if (sValue.length > 0 || _EdsSearchType == 2) {
            if (sValue == '请输入起点' || sValue == '请输入目的地') {
                vEdushiMap.$('txtPopSearchInput').value = '';
                return;
            }
            var mapUrl = 'http://' + _EdsMap.CityCode + '.edushi.com/'
            if (_EdsSearchType == 0) {//两点公交
                var StartStation = sValue;
                var EndStation = _EdsPopInfoData.ShowName;
                mapUrl += '?Key1=' + escape(StartStation) + '&Key2=' + escape(EndStation) + '&stype=deuce&btype=bus';
                window.open(mapUrl);
            }
            else if (_EdsSearchType == 1) {//两点公交
                var StartStation = _EdsPopInfoData.ShowName;
                var EndStation = sValue;
                mapUrl += '?Key1=' + escape(StartStation) + '&Key2=' + escape(EndStation) + '&stype=deuce&btype=bus';
                window.open(mapUrl);
            }
            else {
                //var StartStation = _EdsPopInfoData.ShowName;
                //var EndStation = sValue;
                mapUrl += '?Key1=' + _EdsPopInfoData.CX + ',' + _EdsPopInfoData.CY + '&stype=zbBus&btype=bus' + '&key2=' + escape(_EdsPopInfoData.ShowName);
                window.open(mapUrl);
            }
        }
        else {
            alert('请输入关键词');
            vEdushiMap.$('txtPopSearchInput').focus();
        }
    }
};


function fnSetJobPoint() {
    var tip = '<div style="height:15px; line-height:15px; border:1px solid #1A70C7; background:#fff; padding:0 4px 0 4px;filter:alpha(opacity=90); opacity:.9; float:left; color:#000; font-size:12px; white-space:nowrap;">单击左键选择位置</div>';
    vEdushiMap.ShowPointerTip(tip);
    _state = true;
};

function fnShowPop() {
    var x = vEdushiMap.PointerX();
    var y = vEdushiMap.PointerY();

    if (vEdushiMap.$('Pop')) {
        vEdushiMap.RemoveEntity(vEdushiMap.$('Pop'));
    }
    _PopLayer = vEdushiMap.NewMapLayer('Pop', 269, true); //标记坐标图层
    var sHtml = '<span style="font-size:14px;font-weight:bold;margin:5px 0px 2px 20px;float:left;">该点坐标为：</span></br>';
    sHtml += '<div style="font-size:13px;clear:both;margin:5px 5px;3px 5px;">X轴:' + x + ',Y轴:' + y + '</div></br>';
    sHtml += '<img src="http://dw.edushi.com/Images/btn_ok1.gif" style="cursor:pointer;" onclick="parent.fnClosePop(' + x + ',' + y + ');" />';
    var jobdmark = vEdushiMap.$C('div');
    jobdmark.id = "jobPoint";
    jobdmark.className = 'tishipaopao';
    jobdmark.innerHTML = sHtml;
    vEdushiMap.AppendEntity(jobdmark, _PopLayer, false, x, y, 213, 82, 0, 60, false);
    vEdushiMap.HidePointerTip();
    _state = false;
};
function fnClosePop(x, y) {
    if (vEdushiMap.$('Pop')) {
        vEdushiMap.RemoveEntity(vEdushiMap.$('Pop'));
        document.getElementById('showXY').innerHTML = 'X:' + x + ',Y:' + y;
    }
}
var _EdsSearchType = 0;

//关闭默认定位泡泡
function ClosePaoPao() {
    if (vEdushiMap.$('paopaoInfo')) {
        vEdushiMap.RemoveEntity(vEdushiMap.$('paopaoInfo'));
    }
}
//关闭实体泡泡
function CloasEntityPaoPao() {
    if (vEdushiMap.$('paoPaoInfo')) {
        vEdushiMap.RemoveEntity(vEdushiMap.$('paoPaoInfo'));
    }
}

function SubStr(oldStr, length) {
    var newStr = '';
    if (oldStr.length > length) {
        newStr = oldStr.substring(0, length) + '...';
    }
    else {
        newStr = oldStr;
    }
    return newStr;
}


function cityCheckIn(city) {
    var cities = switchCity.split('|');
    var bol = false;
    if (cities.length > 0) {
        for (var i = 0; i < cities.length; i++) {
            if (cities[i] == city) {
                bol = true;
                break;
            }
        }
    }
    return bol;
}