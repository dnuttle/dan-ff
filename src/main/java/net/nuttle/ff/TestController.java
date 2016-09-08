/*
 * ADOBE CONFIDENTIAL
 * __________________
 *
 * Copyright 2016 Adobe Systems Incorporated
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 */
package net.nuttle.ff;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Adobe Systems Inc
 *
 */
@RestController
public class TestController {

  @RequestMapping(value="/timer/{action:set}/{hour:\\d\\d}/{min:\\d\\d}", method=RequestMethod.POST)
  public String setTimer(@PathVariable("action") String action, @PathVariable("hour") int hour, @PathVariable("min") int min) {
    return "Setting timer to " + hour + ":" + min;
  }
  
  @RequestMapping(value="/timer/clear", method=RequestMethod.POST)
  public String clearTimer() {
    return "Clearing timer\n";
  }
  
  @RequestMapping(value="/timer/check", method=RequestMethod.GET) 
  public String checkTimer() {
    return "Checking state of timer";
  }
  
  @RequestMapping(value="/lastfeed", method=RequestMethod.GET)
  public String lastFeed() {
    return "Checking last feed time";
  }
  
  
}
