/*
* Copyright (C) 2010 Mamadou Diop.
*
* Contact: Mamadou Diop <diopmamadou(at)doubango.org>
*	
* This file is part of imsdroid Project (http://code.google.com/p/imsdroid)
*
* imsdroid is free software: you can redistribute it and/or modify it under the terms of 
* the GNU General Public License as published by the Free Software Foundation, either version 3 
* of the License, or (at your option) any later version.
*	
* imsdroid is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
* without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
* See the GNU General Public License for more details.
*	
* You should have received a copy of the GNU General Public License along 
* with this program; if not, write to the Free Software Foundation, Inc., 
* 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*
*/
package org.doubango.imsdroid.Sevices.Impl;

import java.io.IOException;

import org.doubango.imsdroid.R;
import org.doubango.imsdroid.Services.ISoundService;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

//FIXME: refactor, use ON stop() and ONE start()

public class SoundService extends Service implements ISoundService {

	private MediaPlayer dtmfPlayer;
	private MediaPlayer ringTonePlayer;
	private MediaPlayer ringBackTonePlayer;
	private MediaPlayer smsPlayer;
	private MediaPlayer connPlayer;

	@Override
	public void playDTMF(int number) {
		if(this.dtmfPlayer == null){
			this.dtmfPlayer = new MediaPlayer();
		}
		
		int rawId = -1;
		
		switch(number){
			case 0:
				rawId = R.raw.dtmf_0;
				break;
			case 1:
				rawId = R.raw.dtmf_1;
				break;
			case 2:
				rawId = R.raw.dtmf_2;
				break;
			case 3:
				rawId = R.raw.dtmf_3;
				break;
			case 4:
				rawId = R.raw.dtmf_4;
				break;
			case 5:
				rawId = R.raw.dtmf_5;
				break;
			case 6:
				rawId = R.raw.dtmf_6;
				break;
			case 7:
				rawId = R.raw.dtmf_7;
				break;
			case 8:
				rawId = R.raw.dtmf_8;
				break;
			case 9:
				rawId = R.raw.dtmf_9;
				break;
			case 10:
				rawId = R.raw.dtmf_star;
				break;
			case 11:
				rawId = R.raw.dtmf_pound;
				break;
		}
		
		if(rawId != -1){
			try{
				this.dtmfPlayer.stop();
				this.dtmfPlayer.reset();
				
				AssetFileDescriptor afd = ServiceManager.getMainActivity().getResources().openRawResourceFd(rawId);
				this.dtmfPlayer.setDataSource(afd.getFileDescriptor(),
	                    afd.getStartOffset(),
	                    afd.getLength());
				afd.close();
				this.dtmfPlayer.prepare();
				this.dtmfPlayer.start();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void stopDTMF() {
		if(this.dtmfPlayer == null){
			return;
		}
		
		try{
			this.dtmfPlayer.stop();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void playRingTone() {
		if(this.ringTonePlayer == null){
			this.ringTonePlayer  = MediaPlayer.create(ServiceManager.getMainActivity(), R.raw.ringtone);
		}
		else{
			this.ringTonePlayer.reset();
			try {
				AssetFileDescriptor afd = ServiceManager.getMainActivity().getResources().openRawResourceFd(R.raw.ringtone);
				this.ringTonePlayer.setDataSource(afd.getFileDescriptor(),
	                    afd.getStartOffset(),
	                    afd.getLength());
				afd.close();
				this.ringTonePlayer.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try{
			this.ringTonePlayer.setLooping(true);
			this.ringTonePlayer.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stopRingTone() {
		if(this.ringTonePlayer == null){
			return;
		}
		
		try{
			this.ringTonePlayer.stop();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void playRingBackTone() {
		if(this.ringBackTonePlayer == null){
			this.ringBackTonePlayer  = MediaPlayer.create(ServiceManager.getMainActivity(), R.raw.ringbacktone);
		}
		else{
			this.ringBackTonePlayer.reset();
			try {
				AssetFileDescriptor afd = ServiceManager.getMainActivity().getResources().openRawResourceFd(R.raw.ringbacktone);
				this.ringBackTonePlayer.setDataSource(afd.getFileDescriptor(),
	                    afd.getStartOffset(),
	                    afd.getLength());
				afd.close();
				this.ringBackTonePlayer.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try{
			this.ringBackTonePlayer.setLooping(true);
			this.ringBackTonePlayer.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stopRingBackTone() {
		if(this.ringBackTonePlayer == null){
			return;
		}
		
		try{
			this.ringBackTonePlayer.stop();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void playConnectionChanged(boolean connected) {
	}
	
	@Override
	public void stopConnectionChanged(boolean connected) {
	}

	@Override
	public void stopNewSMS() {
		if(this.smsPlayer == null){
			return;
		}
		
		try{
			this.smsPlayer.stop();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void playNewSMS() {
		if(this.smsPlayer == null){
			this.smsPlayer  = MediaPlayer.create(ServiceManager.getMainActivity(), R.raw.smsevent);
		}
		else{
			this.smsPlayer.reset();
			try {
				AssetFileDescriptor afd = ServiceManager.getMainActivity().getResources().openRawResourceFd(R.raw.smsevent);
				this.smsPlayer.setDataSource(afd.getFileDescriptor(),
	                    afd.getStartOffset(),
	                    afd.getLength());
				afd.close();
				this.smsPlayer.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try{
			this.smsPlayer.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean start() {
		return true;
	}

	@Override
	public boolean stop() {
		return true;
	}
}