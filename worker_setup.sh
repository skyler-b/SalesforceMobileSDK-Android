#!/bin/bash

. android-settings.sh


project_libs="libs/SalesforceSDK external/cordova/framework native/SampleApps/FileExplorer native/SampleApps/RestExplorer libs/SmartStore"

for d in $project_libs ; do
	echo "Updating android project $d"
	(cd $d && android update project -p .)
done

tools/sdk.sh -v -b Cordova SmartStore

# FileExplorer
. android-settings.sh && ./gradlew :native:SampleApps:FileExplorer:assembleDebug

# RestExplorer
. android-settings.sh && ./gradlew :native:SampleApps:RestExplorer:assembleDebug

# SalesforceSDK
. android-settings.sh && ./gradlew :libs:SalesforceSDK:assembleDebug