package com.steria.BizTalk.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.steria.BizTalk.dao.TrackingDAO;
import com.steria.BizTalk.dto.SiteInformation;

/**
 * @author ndevados
 *
 */
@Service
public class TripReadService {

	@Autowired
	TrackingDAO trackingDao;

	private static final Logger logger = LoggerFactory.getLogger(TripReadService.class);

	public void readFiles(String inpath, String fileType) {

		logger.debug("Parsing started");
		List<SiteInformation> sitelist = new ArrayList<>();
		SiteInformation siteInformation = new SiteInformation();
		String routeId = null;
		File Folder = new File(inpath);
		File files[];
		files = Folder.listFiles();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			if (files.length > 0) {
				for (File file : files) {
					if (file.length() != 0) {
						Document doc = dBuilder.parse(file);
						doc.getDocumentElement().normalize();

						NodeList journyList = doc.getElementsByTagName("journey");

						// ------------------------------------------------------------

						for (int temp = 0; temp < journyList.getLength(); temp++) {
							System.out.println("journy loop");
							Node nNode = journyList.item(temp);
							Element eElement = (Element) nNode;
							NodeList siteList = eElement.getElementsByTagName("site");
							for (int j = 0; j < siteList.getLength(); j++) {
								Node siteNode = siteList.item(j);
								Element siteElement = (Element) siteNode;
								sitelist.add(setValueToPojo(eElement, siteElement, siteInformation, fileType));
								routeId = siteInformation.getRouteId();
								siteInformation = new SiteInformation();
							}
						}
						trackingDao.addSiteInfo(sitelist, routeId, fileType);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		logger.debug("Parsing end");
	}

	@SuppressWarnings("deprecation")
	private SiteInformation setValueToPojo(Element eElement, Element siteElement, SiteInformation siteInformation,
			String fileType) throws DOMException, ParseException {

		siteInformation.setRouteId(eElement.getElementsByTagName("routeId").item(0) != null
				? eElement.getElementsByTagName("routeId").item(0).getTextContent() : null);

		siteInformation.setJourneyAlias(eElement.getElementsByTagName("journeyAlias").item(0) != null
				? eElement.getElementsByTagName("journeyAlias").item(0).getTextContent() : null);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		siteInformation
				.setJourneyStart(format.parse(eElement.getElementsByTagName("journeyStart").item(0).getTextContent()));

		siteInformation.setPlannedDistance(eElement.getElementsByTagName("plannedDistance").item(0) != null
				? eElement.getElementsByTagName("plannedDistance").item(0).getTextContent() : null);

		if (!StringUtils.isEmpty(siteElement.getAttribute("id"))) {
			siteInformation.setSite(siteElement.getAttribute("id"));
		}

		NodeList resourceList = eElement.getElementsByTagName("resource");
		for (int k = 0; k < resourceList.getLength(); k++) {
			Node resourceNode = resourceList.item(k);
			Element resourceElement = (Element) resourceNode;

			if ("DRIVER".equalsIgnoreCase(resourceElement.getAttribute("type"))) {
				siteInformation.setResourceDriver(resourceElement.getAttribute("id"));
			} else if ("VEHICLE".equalsIgnoreCase(resourceElement.getAttribute("type"))) {
				siteInformation.setVehicle(resourceElement.getAttribute("id"));
			} else {
				System.out.println("SiteId" + resourceElement.getAttribute("id"));
			}
		}

		siteInformation.setPta(siteElement.getElementsByTagName("pta").item(0) != null
				? siteElement.getElementsByTagName("pta").item(0).getTextContent() : null);
		siteInformation.setPtd(siteElement.getElementsByTagName("ptd").item(0) != null
				? siteElement.getElementsByTagName("ptd").item(0).getTextContent() : null);
		siteInformation.setCallSequence(siteElement.getElementsByTagName("callSequence").item(0) != null
				? siteElement.getElementsByTagName("callSequence").item(0).getTextContent() : null);
		siteInformation.setSiteName(siteElement.getElementsByTagName("siteName").item(0) != null
				? siteElement.getElementsByTagName("siteName").item(0).getTextContent() : null);

		siteInformation.setSiteCategory(siteElement.getElementsByTagName("siteCategory").item(0) != null
				? siteElement.getElementsByTagName("siteCategory").item(0).getTextContent() : null);

		siteInformation.setSiteAddress1(siteElement.getElementsByTagName("siteAddress1").item(0) != null
				? siteElement.getElementsByTagName("siteAddress1").item(0).getTextContent() : null);

		siteInformation.setSiteCity(siteElement.getElementsByTagName("siteCity").item(0) != null
				? siteElement.getElementsByTagName("siteCity").item(0).getTextContent() : null);

		siteInformation.setSitePostCode(siteElement.getElementsByTagName("sitePostCode").item(0) != null
				? siteElement.getElementsByTagName("sitePostCode").item(0).getTextContent() : null);
		siteInformation.setLatitude(siteElement.getElementsByTagName("latitude").item(0) != null
				? siteElement.getElementsByTagName("latitude").item(0).getTextContent() : null);
		siteInformation.setLongitude(siteElement.getElementsByTagName("longitude").item(0) != null
				? siteElement.getElementsByTagName("longitude").item(0).getTextContent() : null);

		if ("process".equalsIgnoreCase(fileType)) {
			siteInformation.setStatus("Processed");
		} else {
			siteInformation.setStatus("Unprocessed");
		}

		return siteInformation;

	}

}