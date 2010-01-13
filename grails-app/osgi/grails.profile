# boot delegation is currently required for groovy to work
# without NoClassDefFound exceptions
-Dorg.osgi.framework.bootdelegation=*
#-Dosgi.java.profile.bootdelegation=*

# logging configuration
scan-composite:${this.relative}/logging.conf

# load basic web app files
scan-composite:${this.relative}/equinox-springdm-web-app.profile
