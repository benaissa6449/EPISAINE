m2=${M2_REPO}
scp -P 3993 -rp ${m2}/edu/ezip/ing1/pds/xmart-zity-backend/1.0-SNAPSHOT \
			student_login_for_pds@172.168.1.206:.m2/repository/edu/ezip/ing1/pds/xmart-zity-backend/

scp -P 3993 -p ${HOME}/wrkspc/i₁/smart-city-by-ezip/xmart-city-backend/main-backend-server.sh	\
			student_login_for_pds@172.168.1.206:smart-city-by-ezip/xmart-city-backend

