m2=${M2_REPO}
scp -P 3993 -rp ${m2}/edu/ezip/ing1/pds/xmart-city-backend/1.0-SNAPSHOT \
			127.0.0.1:.m2/repository/edu/ezip/ing1/pds/xmart-city-backend/

scp -P 3993 -p ${HOME}/wrkspc/i₁/smart-city-by-ezip/xmart-city-backend/main-backend-server.sh	\
			127.0.0.1.120:smart-city-by-ezip/xmart-city-backend

