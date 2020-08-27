package com.wb.controller;

import com.github.pagehelper.PageInfo;
import com.wb.common.Constant;
import com.wb.converter.Converter;
import com.wb.entity.Album;
import com.wb.entity.FTPConfig;
import com.wb.entity.Result;
import com.wb.service.AlbumService;
import com.wb.util.FileUpload;
import com.wb.util.FtpUtils;
import com.wb.vo.ReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.wb.entity.ResultCode.DATA_DELETE_FAILURE;
import static com.wb.entity.ResultCode.PARAM_IS_BLANK;

@Controller
@RequestMapping("album")
public class AlbumController {

    private static final String IMG_PATH = "http://www.lingdu.live:9527/myblog/img/";

    @Autowired
    private AlbumService albumService;
    @Autowired
    private FTPConfig ftpConfig;

    /**
     * 获取数据并跳转相册首页
     *
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("index/{pageNum}")
    public String index(@PathVariable Integer pageNum, Model model) {
        if (pageNum == null)
            pageNum = 1;
        PageInfo pageInfo = new PageInfo(albumService.findAll(pageNum, Constant.ALBUM_PAGE_SIZE));
        model.addAttribute("albumPageInfo", pageInfo);
        return "album";
    }

    @RequestMapping("goAdd")
    public String goAdd() {
        return "add-album";
    }

    @RequestMapping("edit/{albumId}")
    public String edit(@PathVariable Integer albumId, Model model) {
        Album album = albumService.findById(albumId);
        model.addAttribute("album", album);
        return "update-album";
    }

    @RequestMapping("del/{albumId}")
    public String del(@PathVariable Integer albumId) {
        Album album = albumService.findById(albumId);
        if (deleteImg(album.getAlbumImg()))
            albumService.deleteById(albumId);
        return "redirect:/album/index/1";
    }

    @RequestMapping("update")
    public String update(ReqVo reqVo) {
        Album album = Converter.getAlbum(reqVo);
        //获取原图片信息
        Album lastAlbum = albumService.findById(reqVo.getId());
        //判断图片是否修改
        if (!reqVo.getFile().isEmpty()) {
            //判断上传图片是否成功!
            String fileName = FileUpload.upload(ftpConfig, reqVo);
            if (!fileName.equals("")) {
                album.setAlbumImg(fileName);
                //删除原图片
                if (!deleteImg(lastAlbum.getAlbumImg()))//删除失败返回
                    return "redirect:/album/index/1";
            }
        }
        //更新数据库
        albumService.update(album);
        return "redirect:/album/index/1";
    }

    @RequestMapping("add")
    public String add(ReqVo reqVo) {
        Album album = Converter.getAlbum(reqVo);
        //判断上传图片是否成功!
        try {
            String fileName = null;
            if (!reqVo.getFile().isEmpty()) {
                fileName = FileUpload.upload(ftpConfig, reqVo);
            }
            if (!fileName.equals("")) {
                album.setAlbumImg(fileName);
                albumService.insert(album);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/album/index/1";
        }
        return "redirect:/album/index/1";
    }

    @RequestMapping("search")
    public String search(String data, Model model) {
        PageInfo pageInfo = new PageInfo(albumService.fuzzyQuery(data));
        model.addAttribute("albumPageInfo", pageInfo);
        return "album";
    }

    /**
     * 删除图片
     *
     * @param filePath
     */
    private boolean deleteImg(String filePath) {
        int index = filePath.lastIndexOf("/");
        return FtpUtils.deleteFile(ftpConfig, filePath.substring(index + 1));
    }

    @RequestMapping("deleteIds")
    @ResponseBody
    public Result deleteIds(int[] ids) {
        if (ids == null || ids.length == 0) {
            return Result.failure(PARAM_IS_BLANK);
        }
        int num = albumService.deleteByIds(ids);
        if (num <= 0) {
            return Result.failure(DATA_DELETE_FAILURE);
        }
        return Result.success();
    }
}
