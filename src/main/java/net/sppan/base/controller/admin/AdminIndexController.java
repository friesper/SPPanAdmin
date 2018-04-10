package net.sppan.base.controller.admin;

import net.sppan.base.Application;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sppan.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Paths;

@Controller
public class AdminIndexController extends BaseController {

    @Autowired
    ResourceLoader resourceLoader;
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private Environment env;

    @RequestMapping(value = {"/admin/", "/admin/index"})
    public String index() {

        return "admin/index";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {

        try {
            String  root=env.getProperty("upload.path");
            logger.debug("file:" + Paths.get(root, filename).toString());
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(root, filename).toString()));

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
