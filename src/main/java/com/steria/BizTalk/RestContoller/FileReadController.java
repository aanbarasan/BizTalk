package com.steria.BizTalk.RestContoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.steria.BizTalk.service.TripReadService;

/**
 * @author ndevados
 *
 */
@RestController
public class FileReadController {

	@Value("${processed.folder.path}")
	private String processedDirPath;

	@Value("${unprocessed.folder.path}")
	private String UnprocessedDirPath;

	@Autowired
	TripReadService tripReadObj;

	@RequestMapping("/readFiles")
	public String readefile(@RequestParam(required = true) String fileType) {

		if ("process".equalsIgnoreCase(fileType)) {
			tripReadObj.readFiles(processedDirPath, fileType);
		} else if ("unprocess".equalsIgnoreCase(fileType)) {
			tripReadObj.readFiles(UnprocessedDirPath, fileType);
		} else {
			return "Invalid Request";
		}

		return "Finished";
	}

}