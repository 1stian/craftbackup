package com.onestian.craftbackup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class backup {
	Plugin plugin = craftbackup.plugin;

	public void runBackup() {

		// Getting time and correct time format.
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Calendar cal = Calendar.getInstance();
		String currentTime = dateFormat.format(cal);

		// Getting backup directory
		File dir = craftbackup.plugin.getDataFolder();
		File backupDir = new File(dir + "\backups");
		String backupDirString = dir + "\backups";
		// Creating directory if it doesn't exist.
		if (!(backupDir.exists())) {
			new File(backupDirString).mkdir();
		} else {
			doBackup(currentTime);
		}
	}

	public void doBackup(String time) {
		// Creating zip files with files and directories.
		String serverDir = plugin.getServer().getWorldContainer().toString();
		File zipFile = new File(plugin.getDataFolder() + "\\backups\\" + "backup " + time + ".zip");

		Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {

			@Override
			public void run() {

				try {
					final File zipDir = new File(serverDir);
					String[] dirContent = zipDir.list();

					ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));

					byte[] readBuffer = new byte[2156];
					int bytesIn = 0;

					for (int i = 0; i < dirContent.length; i++) {
						File f = new File(zipDir, dirContent[i]);
						if (f.isDirectory()) {
							// if the File object is a directory, call this
							// function again to add its content recursively
							String filePath = f.getPath();
							zipDir(filePath, zipFile);
							// loop again
							continue;
						}
					}

				} catch (Exception e) {

				}

			}

		}, 10);
	}
}
