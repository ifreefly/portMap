package dataModel;

public class PortMapItem
{
    private String srcIP, desIP;
    private int srcPort, desPort;

    public PortMapItem()
    {
        initial();
    }

    private void initial()
    {
        setSrcIP("None");
        setDesIP("None");
        setSrcPort("0");
        setDesPort("0");
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((desIP == null) ? 0 : desIP.hashCode());
        result = prime * result + desPort;
        result = prime * result + ((srcIP == null) ? 0 : srcIP.hashCode());
        result = prime * result + srcPort;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        PortMapItem other = (PortMapItem) obj;
        if (desIP == null)
        {
            if (other.desIP != null)
            {
                return false;
            }
        } else if (!desIP.equals(other.desIP))
        {
            return false;
        }
        if (desPort != other.desPort)
        {
            return false;
        }
        if (srcIP == null)
        {
            if (other.srcIP != null)
            {
                return false;
            }
        } else if (!srcIP.equals(other.srcIP))
        {
            return false;
        }
        if (srcPort != other.srcPort)
        {
            return false;
        }
        return true;
    }

    public String getSrcIP()
    {
        return srcIP;
    }

    public void setSrcIP(String srcIP)
    {
        this.srcIP = srcIP;
    }

    public String getDesIP()
    {
        return desIP;
    }

    public void setDesIP(String desIP)
    {
        this.desIP = desIP;
    }

    public int getSrcPort()
    {
        return srcPort;
    }

    public void setSrcPort(String srcPort)
    {
        setSrcPort(Integer.valueOf(srcPort));
    }

    public void setSrcPort(int srcPort)
    {
        this.srcPort = srcPort;
    }

    public int getDesPort()
    {
        return desPort;
    }

    public void setDesPort(String desPort)
    {
        this.desPort = Integer.valueOf(desPort);
    }

    public void setDesPort(int desPort)
    {
        this.desPort = desPort;
    }
}
